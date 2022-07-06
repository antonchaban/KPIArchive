create database examDB;

go

use examDB
go

create schema examSc
go


use examDB
go

create table examSc.books
(
    bookID   int identity
        constraint books_pk
            primary key nonclustered,
    bookName nvarchar(45)
)
go

create unique index books_bookID_uindex
    on examSc.books (bookID)
go

create table examSc.classroom
(
    crID   int identity
        constraint classroom_pk
            primary key nonclustered,
    crName nvarchar(45)
)
go

create unique index classroom_crID_uindex
    on examSc.classroom (crID)
go

create table examSc.equipment
(
    eqID   int identity
        constraint equipment_pk
            primary key nonclustered,
    eqName nvarchar(45)
)
go

create table examSc.eqInClassroom
(
    ID   int identity
        constraint eqInClassroom_pk
            primary key nonclustered,
    eqID int
        constraint eqInClassroom_equipment_eqID_fk
            references examSc.equipment,
    crID int
        constraint eqInClassroom_classroom_crID_fk
            references examSc.classroom
)
go

create unique index eqInClassroom_ID_uindex
    on examSc.eqInClassroom (ID)
go

create unique index equipment_eqID_uindex
    on examSc.equipment (eqID)
go

create table examSc.students
(
    studentID      int identity
        constraint students_pk
            primary key nonclustered,
    studentName    nvarchar(40),
    studentSurname nvarchar(40)
)
go

create unique index students_studentID_uindex
    on examSc.students (studentID)
go

create table examSc.subjects
(
    subID    int identity
        constraint subjects_pk
            primary key nonclustered,
    subName  nvarchar(45),
    subHours int
)
go

create table examSc.booksForSubject
(
    ID        int identity
        constraint booksForSubject_pk
            primary key nonclustered,
    bookID    int
        constraint booksForSubject_books_bookID_fk
            references examSc.books,
    subjectID int
        constraint booksForSubject_subjects_subID_fk
            references examSc.subjects
)
go

create unique index booksForSubject_ID_uindex
    on examSc.booksForSubject (ID)
go

create unique index subjects_subID_uindex
    on examSc.subjects (subID)
go

create table examSc.teachers
(
    teacherID      int identity
        constraint teachers_pk
            primary key nonclustered,
    teacherName    nvarchar(45),
    teacherSurname nvarchar(45)
)
go

create table examSc.classes
(
    classID        int identity
        constraint classes_pk
            primary key nonclustered,
    className      nvarchar(5),
    classTeacherID int
        constraint classes_teachers_teacherID_fk
            references examSc.teachers
)
go

create unique index classes_classID_uindex
    on examSc.classes (classID)
go

create table examSc.school
(
    schoolID      int,
    schoolName    nvarchar(50),
    schoolAddress nvarchar(80),
    schoolAdmID   int
        constraint school_teachers_teacherID_fk
            references examSc.teachers
)
go

create unique index school_schoolID_uindex
    on examSc.school (schoolID)
go

create table examSc.studentsInClasses
(
    ID        int identity
        constraint studentsInClasses_pk
            primary key nonclustered,
    studentID int
        constraint studentsInClasses_students_studentID_fk
            references examSc.students,
    classID   int
        constraint studentsInClasses_classes_classID_fk
            references examSc.classes
)
go

create unique index studentsInClasses_ID_uindex
    on examSc.studentsInClasses (ID)
go

create table examSc.subjectsInClasses
(
    ID        int identity
        constraint subjectsInClasses_pk
            primary key nonclustered,
    subjectID int
        constraint subjectsInClasses_subjects_subID_fk
            references examSc.subjects,
    classID   int
        constraint subjectsInClasses_classes_classID_fk
            references examSc.classes
)
go

create unique index subjectsInClasses_ID_uindex
    on examSc.subjectsInClasses (ID)
go

create table examSc.teachSchedule
(
    ID        int identity
        constraint teachSchedule_pk
            primary key nonclustered,
    subjectID int
        constraint teachSchedule_subjects_subID_fk
            references examSc.subjects,
    teacherID int
        constraint teachSchedule_teachers_teacherID_fk
            references examSc.teachers
)
go

create table examSc.schedule
(
    scheduleID      int identity
        constraint schedule_pk
            primary key nonclustered,
    crID            int
        constraint schedule_classroom_crID_fk
            references examSc.classroom,
    classID         int
        constraint schedule_classes_classID_fk
            references examSc.classes,
    teachScheduleID int
        constraint schedule_teachSchedule_ID_fk
            references examSc.teachSchedule,
    time            time
)
go

create unique index schedule_scheduleID_uindex
    on examSc.schedule (scheduleID)
go

create unique index teachSchedule_ID_uindex
    on examSc.teachSchedule (ID)
go

create unique index teachers_teacherID_uindex
    on examSc.teachers (teacherID)
go



create trigger ClassHead_Insert_Update
    on examSc.classes
    after insert , update
    as
begin
    DECLARE @Operation VARCHAR(6)

    IF EXISTS(SELECT 0 FROM inserted)
        BEGIN
            IF EXISTS(SELECT 0 FROM deleted)
                BEGIN
                    SELECT @Operation = 'UPDATE'
                END
            ELSE
                BEGIN
                    SELECT @Operation = 'INSERT'
                END
        END
    ELSE
        BEGIN
            SELECT @Operation = 'DELETE'
        END
    PRINT @Operation

    IF @Operation in ('INSERT', 'UPDATE')
        if exists(select 1
                  from examSc.classes c
                  where c.classTeacherID in (select i.classTeacherID
                                             from inserted i))
            BEGIN
                RAISERROR ('This teacher already have class.' ,10,1)
                ROLLBACK TRANSACTION
            END
end
GO

drop trigger examSc.ClassHead_Insert_Update;

CREATE FUNCTION subjCheck(@tID int)
    RETURNS int
AS
BEGIN
    DECLARE @retval int
    SELECT @retval = COUNT(*) FROM examSc.teachSchedule where teacherID = @tID;
    RETURN @retval
END;
GO
ALTER TABLE examSc.teachSchedule
    ADD CONSTRAINT chkSbjCount CHECK (dbo.subjCheck(teacherID) <= 7);
GO

-- DML

-- a
with q6 as (select ID, c.className, s.subName, siCHours, subjectID
            from examSc.subjectsInClasses
                     inner join examSc.classes c on c.classID = subjectsInClasses.classID
                     inner join examSc.subjects s on s.subID = subjectsInClasses.subjectID
            where c.className like '6%'),
     q7 as (select ID, c.className, s.subName, siCHours, subjectID
            from examSc.subjectsInClasses
                     inner join examSc.classes c on c.classID = subjectsInClasses.classID
                     inner join examSc.subjects s on s.subID = subjectsInClasses.subjectID
            where c.className like '7%')
select string_agg(q7.subName, ', ')
from q6
         inner join q7 on q6.subjectID = q7.subjectID
where q7.siCHours > q6.siCHours;


-- b

select count(1), c.classID
from examSc.subjectsInClasses sbIC
         inner join examSc.classes c on c.classID = sbIC.classID
         inner join examSc.subjects s on s.subID = sbIC.subjectID
where s.subName = 'Polish'
group by c.classID

-- c

select top 1 count(1), t.teacherID
from examSc.schedule sch
         inner join examSc.classes c on c.classID = sch.classID
         inner join examSc.teachSchedule tS on tS.ID = sch.teachScheduleID
         inner join examSc.teachers t on t.teacherID = tS.teacherID
where className like '5%'
   or className like '6%'
   or className like '7%'
group by t.teacherID
order by count(1) desc;

-- d

select top 1 count(1), crID
from examSc.schedule
group by crID
order by count(1) desc;


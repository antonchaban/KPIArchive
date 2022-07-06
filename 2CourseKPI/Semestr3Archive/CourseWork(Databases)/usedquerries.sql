use docFlowDB
go

create table docFlowSc.Addresses
(
    addressID        int identity
        constraint Addresses_pk
            primary key nonclustered,
    addressCityID    int
        constraint Addresses_Cities_cityID_fk
            references docFlowSc.Cities,
    addressStreet    nvarchar(100),
    addressCountryID int
        constraint Addresses_Countries_countryID_fk
            references docFlowSc.Countries,
    addressZIP       nvarchar(5)
)
go

create unique index Addresses_addressID_uindex
    on docFlowSc.Addresses (addressID)
go

use docFlowDB
go

create table docFlowSc.Authors
(
    authorID           int identity
        constraint Authors_pk
            primary key nonclustered,
    isInner            bit,
    authorWorkerID     int
        constraint Authors_Workers_workerID_fk
            references docFlowSc.Workers,
    authorOutName      nvarchar(40),
    authorOutSurname   nvarchar(40),
    authorOutAddressID int
)
go

create unique index Authors_authorID_uindex
    on docFlowSc.Authors (authorID)
go

use docFlowDB
go

create table docFlowSc.CareerLog
(
    careerID        int identity
        constraint CareerLog_pk
            primary key nonclustered,
    careerWorkerID  int
        constraint CareerLog_Workers_workerID_fk
            references docFlowSc.Workers,
    careerRankID    int
        constraint CareerLog_Ranks_rankID_fk
            references docFlowSc.Ranks,
    careerDepID     int
        constraint CareerLog_Departments_depID_fk
            references docFlowSc.Departments,
    careerStartDate date,
    careerEndDate   date
)
go

create unique index CareerLog_careerID_uindex
    on docFlowSc.CareerLog (careerID)
go

use docFlowDB
go

create table docFlowSc.Cities
(
    cityID   int identity
        constraint Cities_pk
            primary key nonclustered,
    cityName nvarchar(80)
)
go

create unique index Cities_cityID_uindex
    on docFlowSc.Cities (cityID)
go

use docFlowDB
go

create table docFlowSc.Countries
(
    countryID   int identity
        constraint Countries_pk
            primary key nonclustered,
    countryName nvarchar(50)
)
go

create unique index Countries_countryID_uindex
    on docFlowSc.Countries (countryID)
go

use docFlowDB
go

create table docFlowSc.Departments
(
    depID   int identity
        constraint Departments_pk
            primary key nonclustered,
    depName nvarchar(45)
)
go

create unique index Departments_depID_uindex
    on docFlowSc.Departments (depID)
go

use docFlowDB
go

create table docFlowSc.docStates
(
    docStateID int identity
        constraint docStates_pk
            primary key nonclustered,
    docState   nvarchar(40)
)
go

create unique index docStates_docStateID_uindex
    on docFlowSc.docStates (docStateID)
go

use docFlowDB
go

create table docFlowSc.DocTypes
(
    docTypeID int identity
        constraint DocTypes_pk
            primary key nonclustered,
    docType   nvarchar(40)
)
go

create unique index DocTypes_docTypeID_uindex
    on docFlowSc.DocTypes (docTypeID)
go

use docFlowDB
go

create table docFlowSc.Documents
(
    docID          int identity
        constraint Documents_pk
            primary key nonclustered,
    docRegNum      int,
    docDate        date,
    docTypeID      int
        constraint Documents_DocTypes_docTypeID_fk
            references docFlowSc.DocTypes,
    docFile        binary(4000),
    docAuthorID    int
        constraint Documents_Authors_authorID_fk
            references docFlowSc.Authors,
    docTitle       nvarchar(200),
    docDescription nvarchar(2000),
    docCurStateID  int
        constraint Documents_docStates_docStateID_fk
            references docFlowSc.docStates
)
go

create unique index Documents_docID_uindex
    on docFlowSc.Documents (docID)
go

use docFlowDB
go

create table docFlowSc.DocWorkFlow
(
    workflowID          int identity
        constraint DocWorkFlow_pk
            primary key nonclustered,
    workflowDocID       int
        constraint DocWorkFlow_Documents_docID_fk
            references docFlowSc.Documents,
    workflowWorkerID    int
        constraint DocWorkFlow_Workers_workerID_fk
            references docFlowSc.Workers,
    workflowDateStart   date,
    workflowDateEnd     date,
    workflowPrevStateID int
        constraint DocWorkFlow_docStates_docStateID_fk
            references docFlowSc.docStates,
    workflowNewStateID  int
        constraint DocWorkFlow_docStates_docStateID_fk_2
            references docFlowSc.docStates
)
go

create unique index DocWorkFlow_workflowID_uindex
    on docFlowSc.DocWorkFlow (workflowID)
go

use docFlowDB
go

create table docFlowSc.PossibleStates
(
    state1ID int not null
        constraint PossibleStates_docStates_docStateID_fk
            references docFlowSc.docStates,
    state2ID int not null
        constraint PossibleStates_docStates_docStateID_fk_2
            references docFlowSc.docStates,
    constraint PK_States
        primary key (state1ID, state2ID)
)
go

use docFlowDB
go

create table docFlowSc.Ranks
(
    rankID   int identity
        constraint Ranks_pk
            primary key nonclustered,
    rankName nvarchar(45)
)
go

create unique index Ranks_rankID_uindex
    on docFlowSc.Ranks (rankID)
go

use docFlowDB
go

create table docFlowSc.Workers
(
    workerID            int identity
        constraint Workers_pk
            primary key nonclustered,
    workerName          nvarchar(40),
    workerSurname       nvarchar(40),
    workerSalary        float,
    workerBonuses       float,
    workerAddressID     int
        constraint Workers_Addresses_addressID_fk
            references docFlowSc.Addresses,
    workerCurrentRankID int
        constraint Workers_Ranks_rankID_fk
            references docFlowSc.Ranks,
    workerCurrentDepID  int
        constraint Workers_Departments_depID_fk
            references docFlowSc.Departments,
    workerDate          date
)
go

create unique index Workers_workerID_uindex
    on docFlowSc.Workers (workerID)
go



-- ###### TRIGGERS START ######

-- Trigger for worker career logging

create trigger Workers_Insert_Update_Delete
    on docFlowSc.Workers
    after insert , update, delete
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
    IF @Operation = 'DELETE'
        insert into docFlowSc.CareerLog (careerWorkerFullName, careerRankID, careerDepID, careerStartDate,
                                         careerEndDate)
        select (select concat(d.workerName, ' ', d.workerSurname)),
               d.workerCurrentRankID,
               d.workerCurrentDepID,
               d.workerDate,
               getdate()
        from deleted d;

    IF @Operation = 'INSERT'
        insert into docFlowSc.CareerLog
        select (select concat(i.workerName, ' ', i.workerSurname)),
               i.workerCurrentRankID,
               i.workerCurrentDepID,
               getdate(),
               null
        from inserted i;

    IF @Operation = 'UPDATE'
        insert into docFlowSc.CareerLog (careerWorkerFullName, careerRankID, careerDepID, careerStartDate,
                                         careerEndDate)
        select (select concat(i.workerName, ' ', i.workerSurname)),
               i.workerCurrentRankID,
               i.workerCurrentDepID,
               d.workerDate,
               null
        from docFlowSc.Workers w
                 inner join deleted d on w.workerID = d.workerID
                 inner join inserted i on w.workerID = i.workerID;
    update docFlowSc.Workers
    set workerDate = getdate()
    where workerID = (select i.workerID
                      from docFlowSc.Workers w
                               inner join inserted i on w.workerID = i.workerID)
end
GO


-- Trigger for document workflow logging

--TODO On delete?


create trigger DocWorkFlowChanges
    on docFlowSc.Documents
    after insert , update, delete
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
    /*IF @Operation = 'DELETE'
        insert into docFlowSc.DocWorkFlow*/

    IF @Operation = 'INSERT'
        insert into docFlowSc.DocWorkFlow (workflowDocID, workflowWorkerID, workflowDateStart, workflowDateEnd,
                                           workflowPrevStateID, workflowNewStateID)
        select i.docID, 4, getdate(), null, null, i.docCurStateID
        from inserted i;


    IF @Operation = 'UPDATE'
        insert into docFlowSc.DocWorkFlow (workflowDocID, workflowWorkerID, workflowDateStart, workflowDateEnd,
                                           workflowPrevStateID, workflowNewStateID)
        select i.docID,
               4,
               d.docDate,
               (select IIF((i.docCurStateID = 4 or i.docCurStateID = 7 or i.docCurStateID = 8),
                           getdate(), null)),
               d.docCurStateID,
               i.docCurStateID
        from docFlowSc.Documents doc
                 inner join deleted d on doc.docID = d.docID
                 inner join inserted i on doc.docID = i.docID;
    update docFlowSc.Documents
    set docDate = getdate()
    where docID = (select i.docID
                   from docFlowSc.Documents doc
                            inner join inserted i on doc.docID = i.docID)
end
GO

drop trigger docFlowSc.DocWorkFlowChanges;

-- ###### TRIGGERS END ######


-- ###### FUNCTIONS START ######


-- Function to calculate worker exp on current rank

create function expCalculator(@WorkDate date)
    returns int
as
begin
    declare @ret int;
    select @ret = datediff(year, @WorkDate, getdate());
    return @ret;
end

-- Function to concatenate Name + Surname and get worker Full Name

create function fullNamer(@name nvarchar(40), @surname nvarchar(40))
    returns nvarchar(85)
as
begin
return concat(@name, ' ', @surname);
end

-- ###### FUNCTIONS END ######

-- ###### DML QUERIES START ######


-- Documents from inner #1
select docRegNum,
       (select concat(W.workerName, ' ', W.workerSurname)) workerFullName,
       DT.docType,
       docTitle,
       dS.docState
from docFlowSc.Documents
         inner join docFlowSc.Authors A on A.authorID = Documents.docAuthorID
         inner join docFlowSc.Workers W on W.workerID = A.authorWorkerID
         inner join docFlowSc.DocTypes DT on DT.docTypeID = Documents.docTypeID
         inner join docFlowSc.docStates dS on dS.docStateID = Documents.docCurStateID


-- Docs from outer #2
select docRegNum,
       (select concat(A.authorOutName, ' ', A.authorOutSurname)) workerFullName,
       DT.docType,
       docTitle,
       dS.docState
from docFlowSc.Documents
         inner join docFlowSc.Authors A on A.authorID = Documents.docAuthorID
         inner join docFlowSc.DocTypes DT on DT.docTypeID = Documents.docTypeID
         inner join docFlowSc.docStates dS on dS.docStateID = Documents.docCurStateID
where isInner = 0;

-- Workers from Ukraine #3

select (select concat(W.workerName, ' ', W.workerSurname)),
       R2.rankName,
       C.countryName,
       C2.cityName,
       A.addressStreet
from docFlowSc.Workers W
         inner join docFlowSc.Addresses A on A.addressID = W.workerAddressID
         inner join docFlowSc.Countries C on C.countryID = A.addressCountryID
         inner join docFlowSc.Cities C2 on C2.cityID = A.addressCityID
         inner join docFlowSc.Ranks R2 on R2.rankID = W.workerCurrentRankID
where countryName = 'Ukraine';

-- Workers w/ exp < 10 #4

select dbo.fullNamer(W.workerName, W.workerSurname) FullName,
       dbo.expCalculator(w.workerDate) WorkerExp,
       R2.rankName,
       C.countryName,
       C2.cityName
from docFlowSc.Workers W
         inner join docFlowSc.Ranks R2 on R2.rankID = W.workerCurrentRankID
         inner join docFlowSc.Addresses A on A.addressID = W.workerAddressID
         inner join docFlowSc.Countries C on C.countryID = A.addressCountryID
         inner join docFlowSc.Cities C2 on C2.cityID = A.addressCityID
where dbo.expCalculator(w.workerDate) < 10;

-- Workers w/ salary >15k and exp <10yr #5

select dbo.fullNamer(W.workerName, W.workerSurname) FullName,
       dbo.expCalculator(w.workerDate)                     WorkerExp,
       W.workerSalary,
       R2.rankName,
       C.countryName,
       C2.cityName
from docFlowSc.Workers W
         inner join docFlowSc.Ranks R2 on R2.rankID = W.workerCurrentRankID
         inner join docFlowSc.Addresses A on A.addressID = W.workerAddressID
         inner join docFlowSc.Countries C on C.countryID = A.addressCountryID
         inner join docFlowSc.Cities C2 on C2.cityID = A.addressCityID
where dbo.expCalculator(w.workerDate) < 10
  and W.workerSalary > 15000;

-- Workers w/ exp less then avg exp #6

with seniors as (
    select avg(dbo.expCalculator(workerDate)) as avgexp
    from docFlowSc.Workers
)
select dbo.fullNamer(W.workerName, W.workerSurname) FullName,
       dbo.expCalculator(w.workerDate)                     WorkerExp,
       W.workerSalary,
       R2.rankName,
       C.countryName,
       C2.cityName
from docFlowSc.Workers W
         inner join docFlowSc.Ranks R2 on R2.rankID = W.workerCurrentRankID
         inner join docFlowSc.Addresses A on A.addressID = W.workerAddressID
         inner join docFlowSc.Countries C on C.countryID = A.addressCountryID
         inner join docFlowSc.Cities C2 on C2.cityID = A.addressCityID
where dbo.expCalculator(w.workerDate) < (select avgexp from seniors)

-- Workers which country have 'i' in the middle and begins w/ 'U' #7

select dbo.fullNamer(W.workerName, W.workerSurname) FullName,
       R2.rankName,
       C.countryName,
       C2.cityName
from docFlowSc.Workers W
         inner join docFlowSc.Ranks R2 on R2.rankID = W.workerCurrentRankID
         inner join docFlowSc.Addresses A on A.addressID = W.workerAddressID
         inner join docFlowSc.Countries C on C.countryID = A.addressCountryID
         inner join docFlowSc.Cities C2 on C2.cityID = A.addressCityID
where countryName like '%i%'
  and countryName like 'U%';


-- Workers which worked w/ documents #8

select dbo.fullNamer(W.workerName, W.workerSurname) FullName, d.docTitle, ds.docState
from docFlowSc.Workers W
         inner join docFlowSc.DocWorkFlow DWF on W.workerID = DWF.workflowWorkerID
         inner join docFlowSc.Documents D on D.docID = DWF.workflowDocID
         inner join docFlowSc.docStates dS on dS.docStateID = D.docCurStateID

-- Workers which were fired #9

select careerWorkerFullName, D.depName, R2.rankName, careerStartDate, careerEndDate
from docFlowSc.CareerLog
         inner join docFlowSc.Departments D on D.depID = CareerLog.careerDepID
         inner join docFlowSc.Ranks R2 on R2.rankID = CareerLog.careerRankID
where careerEndDate is not null;

-- Documents w/ date between 2000-10-23 and cur date #10

select docRegNum, DT.docType, dS.docState, docDate
from docFlowSc.Documents
         inner join docFlowSc.DocTypes DT on DT.docTypeID = Documents.docTypeID
         inner join docFlowSc.docStates dS on dS.docStateID = Documents.docCurStateID
where docDate between '2000-10-23' and getdate();

-- Top 3 best salaries #11

select top 3 dbo.fullNamer(W.workerName, W.workerSurname), workerSalary, R2.rankName, D.depName
from docFlowSc.Workers W
         inner join docFlowSc.Ranks R2 on R2.rankID = W.workerCurrentRankID
         inner join docFlowSc.Departments D on D.depID = W.workerCurrentDepID
order by workerSalary desc;

-- Top 5 best bonuses #12

select top 5 dbo.fullNamer(W.workerName, W.workerSurname) FullName,
             workerSalary,
             workerBonuses,
             R2.rankName,
             D.depName
from docFlowSc.Workers W
         inner join docFlowSc.Ranks R2 on R2.rankID = W.workerCurrentRankID
         inner join docFlowSc.Departments D on D.depID = W.workerCurrentDepID
order by workerBonuses desc;

-- Work history of Josh Bush #13

select careerWorkerFullName, R2.rankName, D.depName, careerStartDate
from docFlowSc.CareerLog
inner join docFlowSc.Ranks R2 on R2.rankID = CareerLog.careerRankID
inner join docFlowSc.Departments D on D.depID = CareerLog.careerDepID
where careerWorkerFullName = (select dbo.fullNamer(W.workerName, W.workerSurname) from docFlowSc.Workers W where workerID = 41 )
order by careerStartDate asc;

-- Workers which ZIP Code begins w/ 4 #14

select dbo.fullNamer(W.workerName, W.workerSurname) FullName, C.cityName, A.addressZIP
from docFlowSc.Workers W
inner join docFlowSc.Addresses A on A.addressID = W.workerAddressID
inner join docFlowSc.Cities C on C.cityID = A.addressCityID
where addressZIP like '4%';

-- Inner authors w/ most documents #15

select W.workerSurname, count(1) DocCount
from docFlowSc.Documents
inner join docFlowSc.Authors A on A.authorID = Documents.docAuthorID
inner join docFlowSc.Workers W on W.workerID = A.authorWorkerID
where isInner = 1
group by W.workerSurname
order by count(1) desc;

-- Closed docs and its states #16

select D.docTitle, dbo.fullNamer(W.workerName, W.workerSurname) FullName, workflowDateStart ,workflowDateEnd, dS.docState
from docFlowSc.DocWorkFlow
inner join docFlowSc.docStates dS on dS.docStateID = DocWorkFlow.workflowNewStateID
inner join docFlowSc.Documents D on D.docID = DocWorkFlow.workflowDocID
inner join docFlowSc.Workers W on W.workerID = DocWorkFlow.workflowWorkerID
where workflowDateEnd is not null;

-- Workers which works from 2000 year for today #17

select dbo.fullNamer(W.workerName, W.workerSurname) FullName, workerDate, C.cityName, C2.countryName
from docFlowSc.Workers W
inner join docFlowSc.Addresses A on A.addressID = W.workerAddressID
inner join docFlowSc.Cities C on C.cityID = A.addressCityID
inner join docFlowSc.Countries C2 on C2.countryID = A.addressCountryID
where workerDate between '2000-01-01' and getdate();

-- #18 Docs from outer authors w/ word 'Donec' inside

select docRegNum,docDate, A.authorOutSurname, docDescription
from docFlowSc.Documents
inner join docFlowSc.Authors A on A.authorID = Documents.docAuthorID
where docDescription like '%Donec%' and isInner = 0

-- Inner and outer authors union #19

select dbo.fullNamer(W.workerName, W.workerSurname) FullName
from docFlowSc.Authors
inner join docFlowSc.Workers W on W.workerID = Authors.authorWorkerID
union
select dbo.fullNamer(A.authorOutName, A.authorOutSurname) FullName
from docFlowSc.Authors A
where isInner = 0

-- Addresses where street not mentioned #20

select C.cityName, C2.countryName, addressZIP, addressStreet
from docFlowSc.Addresses
inner join docFlowSc.Cities C on C.cityID = Addresses.addressCityID
inner join docFlowSc.Countries C2 on C2.countryID = Addresses.addressCountryID
where addressStreet is not null;

-- ###### DML QUERIES END ######


-- ###### VIEWS START ######


-- View which shows all authors and their status(inner/outer)
create view allAuthors
as
select dbo.fullNamer(W.workerName, W.workerSurname) FullName,
       (select IIF((isInner = 1),
                   'Inner Author', 'Outer Author')) AuthorStatus
from docFlowSc.Authors
         inner join docFlowSc.Workers W on W.workerID = Authors.authorWorkerID
union
select dbo.fullNamer(A.authorOutName, A.authorOutSurname) FullName,
       (select IIF((isInner = 1),
                   'Inner Author', 'Outer Author'))       AuthorStatus
from docFlowSc.Authors A
where isInner = 0

select *
from allAuthors;

-- View which shows all documents and its authors, status, states and regnum

create view documentsInfo
as
select docRegNum,
       DT.docType,
       (select IIF((isInner = 1), dbo.fullNamer(W.workerName, W.workerSurname)
                   , dbo.fullNamer(A.authorOutName, A.authorOutSurname))) AuthorName,
       dS.docState
from docFlowSc.Documents
         inner join docFlowSc.DocTypes DT on DT.docTypeID = Documents.docTypeID
         inner join docFlowSc.Authors A on A.authorID = Documents.docAuthorID
         left join docFlowSc.Workers W on W.workerID = A.authorWorkerID
         inner join docFlowSc.docStates dS on dS.docStateID = Documents.docCurStateID

select * from documentsInfo;

-- Creating user role for viewing only

CREATE LOGIN viewer WITH PASSWORD='123'
GO
USE [docFlowDB]
GO
CREATE USER [viewer] FOR LOGIN [viewer]
GO
USE [docFlowDB]
GO
ALTER ROLE [db_datareader] ADD MEMBER [viewer]
GO

EXECUTE AS USER = 'viewer'

-- Queries to check permissions
SELECT SUSER_NAME(), USER_NAME();
SELECT * FROM fn_my_permissions(null, 'database');

drop user viewer;
drop login viewer;
revert;

-- Accountant user (have access to CRUD tables)

CREATE LOGIN accountant WITH PASSWORD='123'
GO
USE [docFlowDB]
GO
CREATE USER [accountant] FOR LOGIN [accountant]
GO
USE [docFlowDB]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [accountant]
GO

EXECUTE AS USER = 'accountant'
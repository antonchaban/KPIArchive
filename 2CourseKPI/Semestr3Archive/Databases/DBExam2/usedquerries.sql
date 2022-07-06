create database dopkaDB;

use dopkaDB;

create schema dSc;

use dopkaDB
go

create table dSc.academStatus
(
    id     int identity
        constraint academStatus_pk
            primary key nonclustered,
    asName nvarchar(45)
)
go

create unique index academStatus_id_uindex
    on dSc.academStatus (id)
go

create table dSc.city
(
    id       int identity
        constraint city_pk
            primary key nonclustered,
    cityName nvarchar(45)
)
go

create unique index city_id_uindex
    on dSc.city (id)
go

create table dSc.conferences
(
    id        int identity
        constraint conferences_pk
            primary key nonclustered,
    confName  nvarchar(50),
    confTopic nvarchar(45)
)
go

create unique index conferences_id_uindex
    on dSc.conferences (id)
go

create table dSc.country
(
    id          int identity
        constraint country_pk
            primary key nonclustered,
    countryName nvarchar(45)
)
go

create table dSc.addresses
(
    id        int identity
        constraint addresses_pk
            primary key nonclustered,
    cityID    int
        constraint addresses_city_id_fk
            references dSc.city,
    countryID int
        constraint addresses_country_id_fk
            references dSc.country,
    zip       nvarchar(5)
)
go

create unique index addresses_id_uindex
    on dSc.addresses (id)
go

create unique index country_id_uindex
    on dSc.country (id)
go

create table dSc.degrees
(
    id      int identity
        constraint degrees_pk
            primary key nonclustered,
    degName nvarchar(45)
)
go

create unique index degrees_id_uindex
    on dSc.degrees (id)
go

create table dSc.directions
(
    id      int identity
        constraint directions_pk
            primary key nonclustered,
    dirName nvarchar(45)
)
go

create unique index directions_id_uindex
    on dSc.directions (id)
go

create table dSc.ranks
(
    id       int identity
        constraint ranks_pk
            primary key nonclustered,
    rankName nvarchar(50)
)
go

create unique index ranks_id_uindex
    on dSc.ranks (id)
go

create table dSc.thesis
(
    id         int identity
        constraint thesis_pk
            primary key nonclustered,
    thesisName nvarchar(100)
)
go

create unique index thesis_id_uindex
    on dSc.thesis (id)
go

create table dSc.workplaces
(
    id        int identity
        constraint workplaces_pk
            primary key nonclustered,
    placeName nvarchar(50)
)
go

create table dSc.users
(
    id           int identity
        constraint users_pk
            primary key nonclustered,
    name         nvarchar(45),
    surname      nvarchar(45),
    contact      nvarchar(13),
    email        nvarchar(50),
    addressID    int
        constraint users_addresses_id_fk
            references dSc.addresses,
    isSpeaker    bit,
    invite1Date  date,
    invite2Date  date,
    arriveDate   date,
    dispatchDate date,
    hotelReq     bit,
    dirID        int
        constraint users_directions_id_fk
            references dSc.directions,
    degID        int
        constraint users_degrees_id_fk
            references dSc.degrees,
    statusID     int
        constraint users_academStatus_id_fk
            references dSc.academStatus,
    workPlaceID  int
        constraint users_workplaces_id_fk
            references dSc.workplaces,
    rankID       int
        constraint users_ranks_id_fk
            references dSc.ranks
)
go

create table dSc.payments
(
    id          int identity
        constraint payments_pk
            primary key nonclustered,
    payDate     date,
    paySize     float,
    payDeadline date,
    confID      int
        constraint payments_conferences_id_fk
            references dSc.conferences,
    userID      int
        constraint payments_users_id_fk
            references dSc.users
)
go

create unique index payments_id_uindex
    on dSc.payments (id)
go

create unique index users_id_uindex
    on dSc.users (id)
go

create table dSc.usersAtConf
(
    id     int identity
        constraint usersAtConf_pk
            primary key nonclustered,
    userID int
        constraint usersAtConf_users_id_fk
            references dSc.users,
    confID int
        constraint usersAtConf_conferences_id_fk
            references dSc.conferences
)
go

create unique index usersAtConf_id_uindex
    on dSc.usersAtConf (id)
go

create table dSc.usersThesis
(
    id         int identity
        constraint usersThesis_pk
            primary key nonclustered,
    userID     int
        constraint usersThesis_users_id_fk
            references dSc.users,
    thesisID   int
        constraint usersThesis_thesis_id_fk
            references dSc.thesis,
    thesisDate date
)
go

create unique index usersThesis_id_uindex
    on dSc.usersThesis (id)
go

create unique index workplaces_id_uindex
    on dSc.workplaces (id)
go



-- DMLs

-- a

select t.thesisName
from dSc.usersThesis
inner join dSc.thesis t on t.id = usersThesis.thesisID
inner join dSc.users u on u.id = usersThesis.userID
inner join dSc.addresses a on a.id = u.addressID
inner join dSc.city c on c.id = a.cityID
inner join dSc.payments p on u.id = p.userID
where c.cityName = 'Kyiv'
and usersThesis.thesisDate between CAST( DATEADD(Month , -1, GETDATE()) AS Date) and CAST( GETDATE() AS Date)
and p.payDate < usersThesis.thesisDate;


-- b

select u.id, count(1)
from dSc.usersThesis uT
         left join dSc.users u on u.id = uT.userID
where u.invite1Date is null
group by u.id
having count(1) > 3;

-- c

with qImpact as (
    select distinct u.id, count(1)
    from dSc.usersAtConf
    inner join dSc.users u on u.id = usersAtConf.userID
    where u.isSpeaker = 1
    group by u.id
    having count(1) > 3
)
select top 1 count(1), c.cityName
from qImpact qI
inner join dSc.users u on u.id = qI.id
inner join dSc.addresses a on a.id = u.addressID
inner join dSc.city c on c.id = a.cityID
group by c.cityName
order by count(1) desc


-- d

with qDeaded as (select u.id, u.workPlaceID, p.payDate, p.payDeadline
                 from dSc.usersAtConf
                          inner join dSc.users u on u.id = usersAtConf.userID
                          left join dSc.payments p on u.id = p.userID
                 where p.payDate > p.payDeadline or p.payDate is null )
select top 1 count(1), qD.workPlaceID
from qDeaded qD
group by qD.workPlaceID
order by count(1) desc


-- Cursor

create procedure cursorch @confID int
as
Declare @name nvarchar(45)
DECLARE @surname nvarchar(45)
DECLARE @pay float
DECLARE @date1 date
DECLARE @date2 date
declare
    dbCursor CURSOR for
        select u.name, u.surname, p.paySize, p.payDate, u.invite1Date, u.invite2Date
        from dSc.usersAtConf
                 inner join dSc.conferences c on c.id = usersAtConf.confID
                 left join dSc.users u on u.id = usersAtConf.userID
                 inner join dSc.payments p on u.id = p.userID
        where usersAtConf.confID = @confID
open dbCursor
fetch next from dbCursor
while @@FETCH_STATUS = 0
begin
    fetch next from dbCursor into @name, @surname, @pay, @date1, @date2
end
close dbCursor
deallocate dbCursor
go
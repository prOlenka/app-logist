create schema logist;

create table logist.task
(
    id                 uuid not null,
    "startPoint"       int  not null,
    "endPoint"         int  not null,
    "driverName"       text,
    "cargoDescription" text,
    "vehicleNumber"    int  not null
);

create unique index task_id_uindex
    on logist.task (id);

create table "tripEvent"
(
    id          uuid    not null
        constraint tripevent_pk
            primary key,
    trip        text,
    "eventType" text    not null,
    "eventTime" integer not null
);

alter table "tripEvent"
    owner to postgres;

create unique index tripevent_id_uindex
    on "tripEvent" (id);

create table logist.trip
(
    id             uuid not null,
    task           text,
    "creationTime" time not null,
    "startTime"    time not null,
    "endTime"      time not null
);

create unique index trip_id_uindex
    on logist.trip (id);



-- Admins table --
create table admins(
    id serial primary key,
    username varchar not null unique,
    password varchar not null
);

-- Persons --
create table "doctorsCategories"(
    id serial primary key,
    name varchar not null unique
);

create table doctors(
    id serial primary key,
    name varchar not null,
    surname varchar not null,
    birthday date not null,
    "categoryId" int not null
);

create table patients(
    id serial primary key,
    name varchar not null,
    surname varchar not null,
    birthday date not null,
    "doctorId" int
);

create table nurses(
    id serial primary key,
    name varchar not null,
    surname varchar not null,
    birthday date not null
);

-- Medicines --
create table "treatmentsCategories"(
    id serial primary key,
    name varchar not null unique
);

create table treatments(
    id serial primary key,
    name varchar not null,
    "categoryId" int not null,
    unique (name, "categoryId")
);

-- Patient documents --
create table "medicalCards"(
    id serial primary key,
    "patientId" int not null
);

create table "medicalCardsTreatments"(
    "medicalCardId" int not null,
    "treatmentId" int not null,
    primary key ("medicalCardId", "treatmentId")
);

create table discharges(
    id serial primary key,
    "createdAt" timestamptz not null default now(),
    "patientId" int not null,
    diagnosis varchar not null
);

alter table doctors add constraint "doctors_fk0" foreign key ("categoryId") references "doctorsCategories"(id);
alter table patients add constraint "patients_fk0" foreign key ("doctorId") references doctors(id);
alter table treatments add constraint "treatments_fk0" foreign key ("categoryId") references "treatmentsCategories"(id);
alter table "medicalCards" add constraint "medicalCards_fk0" foreign key ("patientId") references patients(id);
alter table "medicalCardsTreatments" add constraint "medicalCardsTreatments_fk0" foreign key ("medicalCardId") references "medicalCards"(id);
alter table "medicalCardsTreatments" add constraint "medicalCardsTreatments_fk1" foreign key ("treatmentId") references treatments(id);
alter table discharges add constraint "discharges_fk0" foreign key ("patientId") references patients(id);
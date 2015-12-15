--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: University; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE "University" IS 'Mentat Nemchinsky:: Project University
';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE groups (
    id bigint NOT NULL,
    groupnumber character varying(10) NOT NULL
);


ALTER TABLE groups OWNER TO postgres;

--
-- Name: groups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE groups_id_seq OWNER TO postgres;

--
-- Name: groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE groups_id_seq OWNED BY groups.id;


--
-- Name: lecture; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE lecture (
    id bigint NOT NULL,
    date timestamp without time zone,
    subject character varying(50),
    professor bigint,
    grup character varying(10),
    room character varying(10)
);


ALTER TABLE lecture OWNER TO postgres;

--
-- Name: lecture_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lecture_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE lecture_id_seq OWNER TO postgres;

--
-- Name: lecture_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lecture_id_seq OWNED BY lecture.id;


--
-- Name: professors; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE professors (
    id bigint NOT NULL,
    firstname character varying(50),
    secondname character varying(50)
);


ALTER TABLE professors OWNER TO postgres;

--
-- Name: professors_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE professors_id_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE professors_id_seq OWNER TO postgres;

--
-- Name: professors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE professors_id_seq OWNED BY professors.id;


--
-- Name: rooms; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE rooms (
    id bigint NOT NULL,
    roomnumber character varying(10) NOT NULL
);


ALTER TABLE rooms OWNER TO postgres;

--
-- Name: rooms_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE rooms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rooms_id_seq OWNER TO postgres;

--
-- Name: rooms_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE rooms_id_seq OWNED BY rooms.id;


--
-- Name: students; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE students (
    id bigint NOT NULL,
    firstname character varying(50),
    secondname character varying(50),
    groupnumber character varying(10)
);


ALTER TABLE students OWNER TO postgres;

--
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE students_id_seq OWNER TO postgres;

--
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE students_id_seq OWNED BY students.id;


--
-- Name: subjects; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE subjects (
    id bigint NOT NULL,
    subjecttitle character varying(50) NOT NULL
);


ALTER TABLE subjects OWNER TO postgres;

--
-- Name: subjects_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE subjects_id_seq OWNER TO postgres;

--
-- Name: subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE subjects_id_seq OWNED BY subjects.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY groups ALTER COLUMN id SET DEFAULT nextval('groups_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lecture ALTER COLUMN id SET DEFAULT nextval('lecture_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY professors ALTER COLUMN id SET DEFAULT nextval('professors_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rooms ALTER COLUMN id SET DEFAULT nextval('rooms_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY students ALTER COLUMN id SET DEFAULT nextval('students_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subjects ALTER COLUMN id SET DEFAULT nextval('subjects_id_seq'::regclass);


--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY groups (id, groupnumber) FROM stdin;
2	221/x
3	333/cpx
4	321/rsdrp
5	666/ussr
\.


--
-- Name: groups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('groups_id_seq', 6, true);


--
-- Data for Name: lecture; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY lecture (id, date, subject, professor, grup, room) FROM stdin;
12	2015-11-25 09:00:00	Phylosophy	112	333/cpx	333
14	2015-11-25 13:00:00	Phylosophy	112	333/cpx	333
15	2015-11-25 09:00:00	Basketball	106	321/rsdrp	404
16	2015-11-26 10:00:00	Physics	109	221/x	202
17	2015-11-26 10:00:00	Literature	107	333/cpx	333
18	2015-11-26 10:00:00	Cryptography	111	666/ussr	505
19	2015-11-25 09:00:00	Giometry	111	666/ussr	505
20	2015-11-26 18:30:00	Giometry	111	666/ussr	505
21	2015-11-26 18:30:00	Giometry	110	666/ussr	404
\.


--
-- Name: lecture_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('lecture_id_seq', 21, true);


--
-- Data for Name: professors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY professors (id, firstname, secondname) FROM stdin;
105	Viktor	Yanukovich
106	Viktor	Jushchenko
107	Stanislav	Marlinskaj
108	Albert	Enstine
109	Nikolo	Tesla
110	Issak	Newton
111	Steve	Jobs
112	Aristotel	Aphinskik
\.


--
-- Name: professors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('professors_id_seq', 112, true);


--
-- Data for Name: rooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY rooms (id, roomnumber) FROM stdin;
2	101
3	202
4	333
5	404
6	505
\.


--
-- Name: rooms_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rooms_id_seq', 6, true);


--
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY students (id, firstname, secondname, groupnumber) FROM stdin;
2007	Roman	Trahtenberg	221/x
2008	Givorad	Slavinskiy	321/rsdrp
2009	Stas	Pjexa	666/ussr
2010	Pamela	Anderson	333/cpx
2013	Vladimir	Lenin	666/ussr
2014	Josif	Jugashvili	666/ussr
2015	Leonid	Bregnev	666/ussr
2016	Vera	Bregneva	333/cpx
2017	Kevin	Mitnik	221/x
2018	Jackob	Hefner	221/x
2019	Bill	Verner	221/x
2020	Bill	Gates	221/x
2021	Leonid	Kravchuk	321/rsdrp
2022	Leonid	Kuchma	321/rsdrp
2023	Fedor	Dvinjatin	321/rsdrp
2011	Sasha	Pavlov	333/cpx
2012	Jessy	Spencer	333/cpx
\.


--
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('students_id_seq', 2023, true);


--
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY subjects (id, subjecttitle) FROM stdin;
3	Phylosophy
79	Literature
2	Basketball
84	Physics
86	Giometry
87	Cryptography
\.


--
-- Name: subjects_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('subjects_id_seq', 90, true);


--
-- Name: groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (groupnumber);


--
-- Name: lecture_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lecture
    ADD CONSTRAINT lecture_pkey PRIMARY KEY (id);


--
-- Name: myanotheruniqueconst; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rooms
    ADD CONSTRAINT myanotheruniqueconst UNIQUE (roomnumber);


--
-- Name: myuniqueconstraint; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY subjects
    ADD CONSTRAINT myuniqueconstraint UNIQUE (subjecttitle);


--
-- Name: professors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY professors
    ADD CONSTRAINT professors_pkey PRIMARY KEY (id);


--
-- Name: rooms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (id);


--
-- Name: students_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


--
-- Name: subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (id);


--
-- Name: lecture_grup_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lecture
    ADD CONSTRAINT lecture_grup_fkey FOREIGN KEY (grup) REFERENCES groups(groupnumber);


--
-- Name: lecture_professor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lecture
    ADD CONSTRAINT lecture_professor_fkey FOREIGN KEY (professor) REFERENCES professors(id);


--
-- Name: lecture_room_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lecture
    ADD CONSTRAINT lecture_room_fkey FOREIGN KEY (room) REFERENCES rooms(roomnumber);


--
-- Name: lecture_subject_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lecture
    ADD CONSTRAINT lecture_subject_fkey FOREIGN KEY (subject) REFERENCES subjects(subjecttitle);


--
-- Name: students_groupnumber_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_groupnumber_fkey FOREIGN KEY (groupnumber) REFERENCES groups(groupnumber) ON UPDATE CASCADE;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


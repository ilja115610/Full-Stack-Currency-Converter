--
-- PostgreSQL database dump
--

-- Dumped from database version 12.8 (Debian 12.8-1.pgdg110+1)
-- Dumped by pg_dump version 13.4

-- Started on 2021-09-26 20:49:33

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16385)
-- Name: conversion_record; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.conversion_record (
    id bigint NOT NULL,
    calculated_rate character varying(255),
    conversion_date timestamp without time zone,
    source_currency_id bigint,
    target_currency_id bigint,
    user_id bigint,
    amount character varying(255)
);


ALTER TABLE public.conversion_record OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16388)
-- Name: conversion_record_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.conversion_record_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conversion_record_id_seq OWNER TO postgres;

--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 203
-- Name: conversion_record_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.conversion_record_id_seq OWNED BY public.conversion_record.id;


--
-- TOC entry 204 (class 1259 OID 16390)
-- Name: currency; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.currency (
    id bigint NOT NULL,
    base_rate character varying(255),
    currency_code character varying(255),
    currency_name character varying(255),
    rate_date date,
    rate_time time without time zone
);


ALTER TABLE public.currency OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16396)
-- Name: currency_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.currency_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.currency_id_seq OWNER TO postgres;

--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 205
-- Name: currency_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.currency_id_seq OWNED BY public.currency.id;


--
-- TOC entry 206 (class 1259 OID 16398)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.users (
    id bigint NOT NULL,
    email character varying(255),
    login character varying(255),
    password character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16404)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 207
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 2842 (class 2604 OID 16406)
-- Name: conversion_record id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversion_record ALTER COLUMN id SET DEFAULT nextval('public.conversion_record_id_seq'::regclass);


--
-- TOC entry 2843 (class 2604 OID 16407)
-- Name: currency id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.currency ALTER COLUMN id SET DEFAULT nextval('public.currency_id_seq'::regclass);


--
-- TOC entry 2844 (class 2604 OID 16408)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 2980 (class 0 OID 16385)
-- Dependencies: 202
-- Data for Name: conversion_record; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.conversion_record (id, calculated_rate, conversion_date, source_currency_id, target_currency_id, user_id, amount) FROM stdin;
1	106.865	2021-09-26 20:47:19.353258	33	9	1	125.236
2	5043457.769	2021-09-26 20:47:48.605242	17	14	1	1478.214
3	14923.255	2021-09-26 20:48:20.460089	4	7	1	874.850
\.


--
-- TOC entry 2982 (class 0 OID 16390)
-- Dependencies: 204
-- Data for Name: currency; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.currency (id, base_rate, currency_code, currency_name, rate_date, rate_time) FROM stdin;
1	1.6165	AUD	Australian dollar	2021-09-26	20:46:23
2	6.2332	BRL	Brazilian real	2021-09-26	20:46:23
3	1.9558	BGN	Bulgarian lev	2021-09-26	20:46:23
4	1.4895	CAD	Canadian dollar	2021-09-26	20:46:23
5	7.5803	CNY	Chinese yuan	2021-09-26	20:46:23
6	7.4955	HRK	Croatian kuna	2021-09-26	20:46:23
7	25.408	CZK	Czech koruna	2021-09-26	20:46:23
8	7.4362	DKK	Danish krone	2021-09-26	20:46:23
9	1	EUR	Euro	2021-09-26	20:46:23
10	9.1240	HKD	Hong Kong dollar	2021-09-26	20:46:23
11	356.99	HUF	Hungarian foint	2021-09-26	20:46:23
12	150.90	ISK	Icelandic krona	2021-09-26	20:46:23
13	86.4710	INR	Indian rupee	2021-09-26	20:46:23
14	16747.11	IDR	Indonesian rupiah	2021-09-26	20:46:23
15	3.7500	ILS	Israeli new shekel	2021-09-26	20:46:23
16	129.49	JPY	Japanese yen	2021-09-26	20:46:23
17	4.9085	MYR	Malaysian ringgit	2021-09-26	20:46:23
18	23.6280	MXN	Mexican peso	2021-09-26	20:46:23
19	1.6687	NZD	New Zealand dollar	2021-09-26	20:46:23
20	10.0890	NOK	Norwegian krone	2021-09-26	20:46:23
21	59.597	PHP	Philippine peso	2021-09-26	20:46:23
22	4.6047	PLN	Polish zloty	2021-09-26	20:46:23
23	0.85703	GBP	Pound sterling	2021-09-26	20:46:23
24	4.9508	RON	Romanian leu	2021-09-26	20:46:23
25	85.5104	RUB	Russian ruble	2021-09-26	20:46:23
26	1.5865	SGD	Singapore dollar	2021-09-26	20:46:23
27	17.5453	ZAR	South African rand	2021-09-26	20:46:23
28	1382.54	KRW	South Korean won	2021-09-26	20:46:23
29	10.1380	SEK	Swedish krone	2021-09-26	20:46:23
30	1.0830	CHF	Swiss franc	2021-09-26	20:46:23
31	39.200	THB	Thai baht	2021-09-26	20:46:23
32	10.3590	TRY	Turkish lira	2021-09-26	20:46:23
33	1.1719	USD	US Dollar	2021-09-26	20:46:23
\.


--
-- TOC entry 2984 (class 0 OID 16398)
-- Dependencies: 206
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, login, password) FROM stdin;
1	user1@user.com	user	user01
\.


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 203
-- Name: conversion_record_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.conversion_record_id_seq', 3, true);


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 205
-- Name: currency_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.currency_id_seq', 33, true);


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 207
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, false);


--
-- TOC entry 2846 (class 2606 OID 16410)
-- Name: conversion_record conversion_record_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversion_record
    ADD CONSTRAINT conversion_record_pkey PRIMARY KEY (id);


--
-- TOC entry 2848 (class 2606 OID 16412)
-- Name: currency currency_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (id);


--
-- TOC entry 2850 (class 2606 OID 16414)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2851 (class 2606 OID 16415)
-- Name: conversion_record fki38u7knba572incxj01ovamni; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversion_record
    ADD CONSTRAINT fki38u7knba572incxj01ovamni FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2852 (class 2606 OID 16420)
-- Name: conversion_record fklpglhmvjosgsm2cfow12phy2v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversion_record
    ADD CONSTRAINT fklpglhmvjosgsm2cfow12phy2v FOREIGN KEY (source_currency_id) REFERENCES public.currency(id);


--
-- TOC entry 2853 (class 2606 OID 16425)
-- Name: conversion_record fks3n297oy5f7aonhba7rgfcvel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversion_record
    ADD CONSTRAINT fks3n297oy5f7aonhba7rgfcvel FOREIGN KEY (target_currency_id) REFERENCES public.currency(id);


ALTER SEQUENCE public.users_id_seq RESTART WITH 2;
ALTER SEQUENCE public.conversion_record_id_seq RESTART WITH 4;
ALTER SEQUENCE public.currency_id_seq RESTART WITH 34;


-- Completed on 2021-09-26 20:49:34

--
-- PostgreSQL database dump complete
--


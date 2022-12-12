--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Drop databases (except postgres and template1)
--

DROP DATABASE spacecodee;




--
-- Drop roles
--

DROP ROLE spacecodee;


--
-- Roles
--

CREATE ROLE spacecodee;
ALTER ROLE spacecodee WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:9xdpihg4Wvjv5FrGgPvTGQ==$QJfetjHMEp1GAG/AuvQAFAqwyOnCcUHcle0ez2CABXs=:YWg1IJmm64f1SBGc7JlfBLQgRo6IDzg79PG6FuiiJoI=';






--
-- Databases
--

--
-- Database "template1" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Debian 14.5-1.pgdg110+1)
-- Dumped by pg_dump version 14.5 (Debian 14.5-1.pgdg110+1)

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

UPDATE pg_catalog.pg_database SET datistemplate = false WHERE datname = 'template1';
DROP DATABASE template1;
--
-- Name: template1; Type: DATABASE; Schema: -; Owner: spacecodee
--

CREATE DATABASE template1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE template1 OWNER TO spacecodee;

\connect template1

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

--
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: spacecodee
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: template1; Type: DATABASE PROPERTIES; Schema: -; Owner: spacecodee
--

ALTER DATABASE template1 IS_TEMPLATE = true;


\connect template1

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

--
-- Name: DATABASE template1; Type: ACL; Schema: -; Owner: spacecodee
--

REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Debian 14.5-1.pgdg110+1)
-- Dumped by pg_dump version 14.5 (Debian 14.5-1.pgdg110+1)

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

DROP DATABASE postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: spacecodee
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE postgres OWNER TO spacecodee;

\connect postgres

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

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: spacecodee
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- PostgreSQL database dump complete
--

--
-- Database "spacecodee" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Debian 14.5-1.pgdg110+1)
-- Dumped by pg_dump version 14.5 (Debian 14.5-1.pgdg110+1)

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

--
-- Name: spacecodee; Type: DATABASE; Schema: -; Owner: spacecodee
--

CREATE DATABASE spacecodee WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE spacecodee OWNER TO spacecodee;

\connect spacecodee

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
-- Name: book; Type: TABLE; Schema: public; Owner: spacecodee
--

CREATE TABLE public.book (
    book_id integer NOT NULL,
    book_author character varying(255) NOT NULL,
    book_description character varying(255) NOT NULL,
    book_name character varying(255) NOT NULL,
    book_pages integer NOT NULL,
    book_url_image character varying(255) NOT NULL,
    book_url_pdf character varying(255) NOT NULL,
    category_book_id integer NOT NULL
);


ALTER TABLE public.book OWNER TO spacecodee;

--
-- Name: book_book_id_seq; Type: SEQUENCE; Schema: public; Owner: spacecodee
--

CREATE SEQUENCE public.book_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.book_book_id_seq OWNER TO spacecodee;

--
-- Name: book_book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: spacecodee
--

ALTER SEQUENCE public.book_book_id_seq OWNED BY public.book.book_id;


--
-- Name: category_book; Type: TABLE; Schema: public; Owner: spacecodee
--

CREATE TABLE public.category_book (
    category_book_id integer NOT NULL,
    category_book_name character varying(100) NOT NULL
);


ALTER TABLE public.category_book OWNER TO spacecodee;

--
-- Name: category_book_category_book_id_seq; Type: SEQUENCE; Schema: public; Owner: spacecodee
--

CREATE SEQUENCE public.category_book_category_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_book_category_book_id_seq OWNER TO spacecodee;

--
-- Name: category_book_category_book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: spacecodee
--

ALTER SEQUENCE public.category_book_category_book_id_seq OWNED BY public.category_book.category_book_id;


--
-- Name: people; Type: TABLE; Schema: public; Owner: spacecodee
--

CREATE TABLE public.people (
    people_id integer NOT NULL,
    people_address character varying(100),
    people_name character varying(200) NOT NULL,
    people_phone integer,
    people_surname character varying(200) NOT NULL
);


ALTER TABLE public.people OWNER TO spacecodee;

--
-- Name: people_people_id_seq; Type: SEQUENCE; Schema: public; Owner: spacecodee
--

CREATE SEQUENCE public.people_people_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.people_people_id_seq OWNER TO spacecodee;

--
-- Name: people_people_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: spacecodee
--

ALTER SEQUENCE public.people_people_id_seq OWNED BY public.people.people_id;


--
-- Name: user_client; Type: TABLE; Schema: public; Owner: spacecodee
--

CREATE TABLE public.user_client (
    user_client_id integer NOT NULL,
    user_client_email character varying NOT NULL,
    user_client_username character varying NOT NULL,
    user_client_password character varying NOT NULL,
    user_role_id integer NOT NULL,
    people_id integer NOT NULL
);


ALTER TABLE public.user_client OWNER TO spacecodee;

--
-- Name: user_client_user_client_id_seq; Type: SEQUENCE; Schema: public; Owner: spacecodee
--

CREATE SEQUENCE public.user_client_user_client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_client_user_client_id_seq OWNER TO spacecodee;

--
-- Name: user_client_user_client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: spacecodee
--

ALTER SEQUENCE public.user_client_user_client_id_seq OWNED BY public.user_client.user_client_id;


--
-- Name: user_rating_book; Type: TABLE; Schema: public; Owner: spacecodee
--

CREATE TABLE public.user_rating_book (
    rating_book_id integer NOT NULL,
    rating_user_id integer NOT NULL,
    rating_book smallint NOT NULL
);


ALTER TABLE public.user_rating_book OWNER TO spacecodee;

--
-- Name: user_role; Type: TABLE; Schema: public; Owner: spacecodee
--

CREATE TABLE public.user_role (
    user_role_id integer NOT NULL,
    user_role_name character varying(100) NOT NULL
);


ALTER TABLE public.user_role OWNER TO spacecodee;

--
-- Name: user_role_user_role_id_seq; Type: SEQUENCE; Schema: public; Owner: spacecodee
--

CREATE SEQUENCE public.user_role_user_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_role_user_role_id_seq OWNER TO spacecodee;

--
-- Name: user_role_user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: spacecodee
--

ALTER SEQUENCE public.user_role_user_role_id_seq OWNED BY public.user_role.user_role_id;


--
-- Name: user_system; Type: TABLE; Schema: public; Owner: spacecodee
--

CREATE TABLE public.user_system (
    user_system_id integer NOT NULL,
    user_system_email character varying(150) NOT NULL,
    user_system_password character varying(255) NOT NULL,
    user_system_username character varying(50) NOT NULL,
    people_id integer NOT NULL
);


ALTER TABLE public.user_system OWNER TO spacecodee;

--
-- Name: user_system_middle_role; Type: TABLE; Schema: public; Owner: spacecodee
--

CREATE TABLE public.user_system_middle_role (
    user_system_id integer NOT NULL,
    user_role_id integer NOT NULL
);


ALTER TABLE public.user_system_middle_role OWNER TO spacecodee;

--
-- Name: user_system_user_system_id_seq; Type: SEQUENCE; Schema: public; Owner: spacecodee
--

CREATE SEQUENCE public.user_system_user_system_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_system_user_system_id_seq OWNER TO spacecodee;

--
-- Name: user_system_user_system_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: spacecodee
--

ALTER SEQUENCE public.user_system_user_system_id_seq OWNED BY public.user_system.user_system_id;


--
-- Name: book book_id; Type: DEFAULT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.book ALTER COLUMN book_id SET DEFAULT nextval('public.book_book_id_seq'::regclass);


--
-- Name: category_book category_book_id; Type: DEFAULT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.category_book ALTER COLUMN category_book_id SET DEFAULT nextval('public.category_book_category_book_id_seq'::regclass);


--
-- Name: people people_id; Type: DEFAULT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.people ALTER COLUMN people_id SET DEFAULT nextval('public.people_people_id_seq'::regclass);


--
-- Name: user_client user_client_id; Type: DEFAULT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_client ALTER COLUMN user_client_id SET DEFAULT nextval('public.user_client_user_client_id_seq'::regclass);


--
-- Name: user_role user_role_id; Type: DEFAULT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_role ALTER COLUMN user_role_id SET DEFAULT nextval('public.user_role_user_role_id_seq'::regclass);


--
-- Name: user_system user_system_id; Type: DEFAULT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_system ALTER COLUMN user_system_id SET DEFAULT nextval('public.user_system_user_system_id_seq'::regclass);


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: spacecodee
--

COPY public.book (book_id, book_author, book_description, book_name, book_pages, book_url_image, book_url_pdf, category_book_id) FROM stdin;
1	Stephen King	It is a 1986 horror novel by American author Stephen King. It was his 22nd book and his 17th novel written under his own name	It	742	https://allbookshub.com/wp-content/uploads/2020/11/It-PDF.png	http://pdf.allbookshub.com/general/IT.pdf	1
2	Anne Rice	It was her debut novel. Based on a short story Rice wrote around 1968, the novel centers on vampire Louis de Pointe du Lac, who tells the story of his life to a reporter.	Interview with the Vampire	371	https://images.cdn1.buscalibre.com/fit-in/360x360/c5/d2/c5d28268d3b115360867a3a55458b6b6.jpg	http://mylanguages.at.ua/Interview_With_The_Vampire-Anne_Rice.pdf	1
3	Gilles	Cansado de vivir en un mundo en el que no encuentra su lugar, y triste por la pérdida de sus seres queridos más cercanos................................................	Dias de perros	12	https://image.cdn0.buscalibre.com/60d16cd38463b5601a8c1b19.__RS360x360__.jpg	https://www.planetadelibros.com.uy/libros_contenido_extra/32/31523_1_30974_DIAS_DE_PERROS_.pdf	1
5	John Kennedy Toole	La conjura de los necios es una novela de John Kennedy Toole, publicada póstumamente en 1980 y ganadora del Pulitzer en 1981. La novela fue traducida al español por Ángela Pérez y José Manuel Álvarez.​	La conjura de los necios	333	https://www.anagrama-ed.es/uploads/media/portadas/0001/15/eec84be551eddec97d987585b2e54afb000c9c96.jpeg	https://www.yumpu.com/es/document/read/14615864/la-conjura-de-los-necios-pdf	2
6	Henry James	trata de una joven institutriz que acude al cuidado de dos niños huérfanos en una vetusta mansión victoriana; lo que en principio parece un cometido...	Otra vuelta a la tuerca	54	https://www.libros-prohibidos.com/wp-content/uploads/2015/08/Otra-vuelta-de-tuerca-portada-300x451.jpg	https://web.seducoahuila.gob.mx/biblioweb/upload/James,%20Henry%20-%20Otra%20vuelta%20de%20tuerca.pdf	1
7	Bram Stoker	La historia comienza con la visita del joven abogado Jonathan Harker al castillo de un conde en Transilvania...	Drácula	230	https://kbimages1-a.akamaihd.net/88a05cf1-a3b6-461b-a8f7-f0e25b06274a/1200/1200/False/dracula-bram-stoker.jpg	https://portalacademico.cch.unam.mx/materiales/al/cont/tall/tlriid/tlriid4/circuloLectores/docs/dracula.pdf	1
8	Lope de Vega	El perro del hortelano es una comedia palatina de Lope de Vega, publicada en la Oncena parte de las comedias de Lope Félix de Vega Carpio en Madrid.	El perro del hortelano	24	https://pics.filmaffinity.com/El_perro_del_hortelano-877265914-large.jpg	http://www.vallecastodocultura.org/Jornada%20de%20Experiencias%20Animacion%20Lectura/2008/perro.pdf	2
9	William Shakespeare	A buen fin no hay mal principio, también traducida como Bien está lo que bien acaba, es una obra de teatro de William Shakespeare. Se piensa que Shakespeare escribió esta obra aproximadamente entre 1601 y 1605.	A buen fin no hay mal principio	76	https://www.elejandria.com/covers/A_buen_fin_no_hay_mal_principio-Shakespeare_William-lg.png	https://biblioteca.org.ar/libros/89148.pdf	2
11	Molière	Tartufo o el impostor es una comedia en cinco actos escrita en versos alejandrinos por Molière y estrenada en París el 5 de febrero de 1669 en el Teatro del Palais-Royal.	Tartufo	31	https://www.imosver.com/es/foto/muestraPortadaEbook.php?id=E0002656709	https://biblioteca.org.ar/libros/150011.pdf	2
12	Molière	El avaro es una comedia en prosa en cinco actos de Molière. Se estrenó en el Teatro del Palais-Royal de París, el 9 de septiembre de 1668. El tema está inspirado en La olla, de Plauto, cuyo protagonista ha cambiado su antiguo nombre de Cornelio por Tulio.	El avaro	62	https://www.crisol.com.pe/media/catalog/product/cache/cf84e6047db2ba7f2d5c381080c69ffe/9/7/9788416775897_aa9otlwgsmbqeniw.jpg	https://www.cjpb.org.uy/wp-content/uploads/repositorio/serviciosAlAfiliado/librosDigitales/Moliere-Avaro.pdf	2
13	Mark Twain	Un yanqui en la corte del rey Arturo es una novela del escritor estadounidense Mark Twain publicada en 1889.	Un yanqui en la corte del rey Arturo	461	http://planetalibro.net/lector/t/w/twain/twain-mark-un-yanqui-en-la-corte-del-rey-arturo/Images/cover.jpg	http://www.librosmaravillosos.com/unyankienlacortedelreyarturo/pdf/Un%20yanqui%20en%20la%20corte%20del%20Rey%20Arturo%20-%20Mark%20Twain.pdf	2
14	Mark Twain	MARK TWAIN. CUENTOS CON HUMOR. AUTOBIOGRAFÍA. Dos o tres personas me escribieron en diferentes ocasiones diciéndome que si yo publicaba mi autobiografía.	Cuentos de humor	12	https://1.bp.blogspot.com/-L4sZfKCBqx8/YVxgF32uf5I/AAAAAAAAA24/MzeTw2S2AVUINv4jkBz-gfYxbisW1wc8gCLcBGAsYHQ/s0/descarga.jpg	https://biblioteca.org.ar/libros/211729.pdf	2
15	Oscar Wilde	La importancia de llamarse Ernesto es una obra teatral de Oscar Wilde escrita en 1895. Es una comedia que trata sobre las costumbres y la seriedad de la sociedad. Está dividida en tres o cuatro actos.	La importancia de ser formal	44	http://www.mscnoticiaslatam.com/wp-content/uploads/2013/03/la-importancia-de-ser-formal-imagen.jpg	https://biblioteca.org.ar/libros/130861.pdf	2
4	Oscar Wilde	The Canterville Ghost (Sir Simon): is the lost soul of a noble	Fantasma de Canterville	46	https://www.elejandria.com/covers/El_fantasma_de_Canterville-Wilde_Oscar-lg.png	weeblebooks.com/juvenil/Oscar_Wilde-El_Fantasma_de_Canterville.pdf	1
16	Stephen King	El misterio de Salem's Lot es la segunda novela del escritor estadounidense Stephen King	El misterio de Salem's Lot	229	https://imagessl9.casadellibro.com/a/l/t5/49/9788466363549.jpg	https://repolibros.files.wordpress.com/2015/05/1975-el-misterio-de-salems-lot-salems-lot.pdf	1
17	 H: P.Lovecraft	narra la expedición desastrosa que realizan unos grupo de expertos a la Antártida, en el que descubren unos seres biológicamente extraordinarios	En las Montañas de la Locura	66	https://luisbermer.com/wp-content/uploads/2017/04/H.P-LOVECRAFT-En-las-monta%C3%B1as-de-la-locura.jpg	https://web.seducoahuila.gob.mx/biblioweb/upload/en_las_monta%C3%83%C2%B1as_de_la_locura.pdf	3
18	Lyman Frank Baum	en la cual una niña estadounidense es arrastrada por un tornado en el estado de Kansas hasta una tierra de fantasía donde habitan brujas buenas y malas	El Maravilloso Mago de Oz	275	https://1.bp.blogspot.com/-VsGDcnRi_x8/XPIJZlrDnjI/AAAAAAAANWw/xOofNFlhv6UJjP8YTxT9DzeScgdAfwnOwCLcBGAs/s1600/5.jpg	http://bibliotecadigital.ilce.edu.mx/Colecciones/CuentosMas/ElMaravillosoMagoOz_Baum.pdf	3
19	Julio Verne	Tras terminar la guerra de Secesión, un grupo de artilleros mutilados decide fabricar un cañón gigante para enviar un proyectil a la Luna...	De la Tierra a la Luna	129	https://1.bp.blogspot.com/-VsGDcnRi_x8/XPIJZlrDnjI/AAAAAAAANWw/xOofNFlhv6UJjP8YTxT9DzeScgdAfwnOwCLcBGAs/s1600/5.jpg	https://biblioteca.org.ar/libros/656256.pdf	3
20	MARY SHELLEY	Tras la prematura muerte de su madre, Víctor Frankenstein se obsesiona con vencer la muerte..	FRANKENSTEIN	168	https://loresumo.com/wp-content/uploads/2020/01/Frankenstein-1.jpg	https://biblioteca.org.ar/libros/167737.pdf	4
21	Savinien de Cyrano de Bergerac	Narra la aventura de un grupo de astrónomos que viaja a la Luna en una cápsula impulsada por cañones...	Viaje a la luna	59	https://www.elejandria.com/covers/Viaje_a_la_luna-Cyrano_de_Bergerac-lg.png	https://biblioteca.org.ar/libros/89939.pdf	4
22	Edgar Rice Burroughs	Decidido a salvar a la princesa, Carthoris llega a la ciudad perdida de Lothar, donde se esforzará para evitar una guerra, tras convertirse en mercenario, que amenaza con poner...	THUVIA, DONCELLA DE MARTE	94	https://assets.lectulandia.com/b/Edgar%20Rice%20Burroughs/Thuvia,%20doncella%20de%20Marte%20(3066)/big.jpg	https://redescol.ilce.edu.mx/20aniversario/componentes/proyec_colab/2005/solaris/redescolar.ilce.edu.mx/redescolar/proyectos/solaris/scifi/thuvia_doncella_de_marte.pdf	4
23	Emilio Salgaris	Trama. India, 1869. Surama, hija del legítimo rajá del Assam, fue vendida como esclava a un grupo de thugs para que la convirtieran en devadasi después de que su tío...	A la Conquista de un Imperio	223	https://www.isliada.org/static/images/2021/08/A-la-conquista-de-un-imperio.jpg	https://biblioteca.org.ar/libros/152622.pdf	5
24	Emilio Salgaris	Esta es una novela de aventura, de acción vertiginosa y trepidante, que cuenta la historia del joven y valiente Hossein -nieto del gran Giah Agha	Águilas de la Estepa	165	https://m.media-amazon.com/images/I/51W2b1m6JwL.jpg	https://biblioteca.org.ar/libros/152874.pdf	5
25	Rudyard Kipling	Kim es una novela picaresca y de espionaje del escritor Sir Rudyard Kipling. Publicada en 1901 por MacMillan & Co. Ltd.,a	Kim	892	https://images.cdn1.buscalibre.com/fit-in/360x360/f9/56/f956af9dbaf478ea15a5c71c6fac168f.jpg	http://www.ataun.eus/BIBLIOTECAGRATUITA/Cl%C3%A1sicos%20en%20Espa%C3%B1ol/Rudyard%20Kipling/Kim.pdf	5
\.


--
-- Data for Name: category_book; Type: TABLE DATA; Schema: public; Owner: spacecodee
--

COPY public.category_book (category_book_id, category_book_name) FROM stdin;
1	Horror
2	comedia
3	Fantasia
4	Ciencia ficción
5	Aventura
\.


--
-- Data for Name: people; Type: TABLE DATA; Schema: public; Owner: spacecodee
--

COPY public.people (people_id, people_address, people_name, people_phone, people_surname) FROM stdin;
2	Piura	Pepito	965412001	Pepito
3	Tumbes	spacecodee	965412548	spacecodee
4	Lima	maria	965412547	maria
5	Renzo-Piura	renzo	954326587	renzo
6	Nomara	nilber	987412563	nilber
7	Piura	juang	926093381	juang
8		Andrea	0	Juarez
9	España	Shakira	965412102	Shakira
10		wakaka	0	wakaka1
11		jose	0	jose
12		remagopa	0	gonzales
\.


--
-- Data for Name: user_client; Type: TABLE DATA; Schema: public; Owner: spacecodee
--

COPY public.user_client (user_client_id, user_client_email, user_client_username, user_client_password, user_role_id, people_id) FROM stdin;
2	maria@gmail.com	maria	$2a$10$ZuSYQd69TUUj3eOsXZN/vuuXKJri5XroX7s9wMWCZG18dGuIO6gXa	3	4
3	andreajuarez@mail.com	andreajuarez	$2a$10$dSom0auOafAg7s9aAeVFgenicRjNODfj0AYIedtnhc1W8WFcacv2S	3	8
4	shakira@email.com	shakira	$2a$10$T3OPLD/WM2MqCoxfItzhKODbQhMpNetEHuRH4XCh0YRfBZF6RD3Ca	3	9
5	wakaka@gmail.com	wakakalol	$2a$10$cnQxqYYeAqCoY2mwzVBwR.u45BjeTRAQZWa8FvcKthwtLYrkr/1L.	3	10
6	jose@mail.com	jose	$2a$10$c2CS/6.fl/0/.rPNj9zQjujAA7JxmvkJ9Undtae6O3K7kFcjKHKi6	3	11
7	remagopa@gmail.com	remagopa01	$2a$10$L8B2aLSEvceRhFSBgVItK.MK0VvjfTIn5e0IKlddlofuffh2IFQLq	3	12
\.


--
-- Data for Name: user_rating_book; Type: TABLE DATA; Schema: public; Owner: spacecodee
--

COPY public.user_rating_book (rating_book_id, rating_user_id, rating_book) FROM stdin;
1	2	4
2	2	3
16	2	4
13	2	4
15	2	2
12	2	2
14	2	5
11	2	3
5	2	4
8	2	5
3	2	4
1	3	5
12	3	5
4	2	2
2	3	5
9	3	3
7	2	5
3	3	5
18	3	5
11	3	4
6	6	3
6	5	2
15	6	4
2	6	4
2	5	3
22	6	2
9	5	5
3	7	5
4	6	4
19	2	4
\.


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: spacecodee
--

COPY public.user_role (user_role_id, user_role_name) FROM stdin;
1	ROLE_ADMIN
2	ROLE_USER
3	ROLE_STUDENT
\.


--
-- Data for Name: user_system; Type: TABLE DATA; Schema: public; Owner: spacecodee
--

COPY public.user_system (user_system_id, user_system_email, user_system_password, user_system_username, people_id) FROM stdin;
1	pepito@gmail.com	$2a$10$oKP8/ycbn31vNJMQmElPG.Xnf0sDNaJqXvLU.BzwVXijBdjp3mRrG	pepito	2
2	spacecodee@gmail.com	$2a$10$cFZMuzhDIXT8Mrn3S1Dzfek4AJG8xPUFyFOg0fbqRanypOPqygTHe	spacecodee	3
3	rgonzalespacherrez@email.com	$2a$10$.U3L1uOo682xu64xC6OUp.2WxGYwee5XKunL9odjTX9qzEPYxsjNW	gonzales	5
4	nilberadrian00@gmail.com	$2a$10$Jlqu2ueslpDPIrKB54RyVOxCRq/c9i6g2XcSJvnMVlJdFhORXO80e	nilber	6
5	juang@email.com	$2a$10$vWIjQN5.njTlceG3XRt2kuoqJBCfhNGysXb/i2kIu5N1NyQrkLOeq	juang	7
\.


--
-- Data for Name: user_system_middle_role; Type: TABLE DATA; Schema: public; Owner: spacecodee
--

COPY public.user_system_middle_role (user_system_id, user_role_id) FROM stdin;
1	2
1	1
2	1
2	2
3	2
3	1
4	2
4	1
5	2
5	1
\.


--
-- Name: book_book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spacecodee
--

SELECT pg_catalog.setval('public.book_book_id_seq', 25, true);


--
-- Name: category_book_category_book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spacecodee
--

SELECT pg_catalog.setval('public.category_book_category_book_id_seq', 5, true);


--
-- Name: people_people_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spacecodee
--

SELECT pg_catalog.setval('public.people_people_id_seq', 12, true);


--
-- Name: user_client_user_client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spacecodee
--

SELECT pg_catalog.setval('public.user_client_user_client_id_seq', 7, true);


--
-- Name: user_role_user_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spacecodee
--

SELECT pg_catalog.setval('public.user_role_user_role_id_seq', 3, true);


--
-- Name: user_system_user_system_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spacecodee
--

SELECT pg_catalog.setval('public.user_system_user_system_id_seq', 5, true);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);


--
-- Name: category_book category_book_pkey; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.category_book
    ADD CONSTRAINT category_book_pkey PRIMARY KEY (category_book_id);


--
-- Name: people people_pkey; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.people
    ADD CONSTRAINT people_pkey PRIMARY KEY (people_id);


--
-- Name: user_client user_client_people_id_key; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT user_client_people_id_key UNIQUE (people_id);


--
-- Name: user_client user_client_pkey; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT user_client_pkey PRIMARY KEY (user_client_id);


--
-- Name: user_client user_client_user_client_email_key; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT user_client_user_client_email_key UNIQUE (user_client_email);


--
-- Name: user_client user_client_user_client_password_key; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT user_client_user_client_password_key UNIQUE (user_client_password);


--
-- Name: user_client user_client_user_client_usernmae_key; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT user_client_user_client_usernmae_key UNIQUE (user_client_username);


--
-- Name: user_rating_book user_rating_book_pkey; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_rating_book
    ADD CONSTRAINT user_rating_book_pkey PRIMARY KEY (rating_book_id, rating_user_id);


--
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_role_id);


--
-- Name: user_system_middle_role user_system_middle_role_pkey; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_system_middle_role
    ADD CONSTRAINT user_system_middle_role_pkey PRIMARY KEY (user_system_id, user_role_id);


--
-- Name: user_system user_system_pkey; Type: CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_system
    ADD CONSTRAINT user_system_pkey PRIMARY KEY (user_system_id);


--
-- Name: book category_book_id___fk; Type: FK CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT category_book_id___fk FOREIGN KEY (category_book_id) REFERENCES public.category_book(category_book_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_client people_id___fk; Type: FK CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT people_id___fk FOREIGN KEY (people_id) REFERENCES public.people(people_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_system people_id___fk; Type: FK CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_system
    ADD CONSTRAINT people_id___fk FOREIGN KEY (people_id) REFERENCES public.people(people_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_rating_book rating_book_id___fk; Type: FK CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_rating_book
    ADD CONSTRAINT rating_book_id___fk FOREIGN KEY (rating_book_id) REFERENCES public.book(book_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_rating_book rating_user_id___fk; Type: FK CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_rating_book
    ADD CONSTRAINT rating_user_id___fk FOREIGN KEY (rating_user_id) REFERENCES public.user_client(user_client_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_client user_role_id___fk; Type: FK CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT user_role_id___fk FOREIGN KEY (user_role_id) REFERENCES public.user_role(user_role_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_system_middle_role user_role_id__fk; Type: FK CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_system_middle_role
    ADD CONSTRAINT user_role_id__fk FOREIGN KEY (user_role_id) REFERENCES public.user_role(user_role_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_system_middle_role user_system_id___fk; Type: FK CONSTRAINT; Schema: public; Owner: spacecodee
--

ALTER TABLE ONLY public.user_system_middle_role
    ADD CONSTRAINT user_system_id___fk FOREIGN KEY (user_system_id) REFERENCES public.user_system(user_system_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--


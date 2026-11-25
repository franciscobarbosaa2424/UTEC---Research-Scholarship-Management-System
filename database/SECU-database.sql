--
-- PostgreSQL database dump
--

--\restrict i0hNBdnlxhQa2OjdFJR5MFUtvHuv03kvVbyZrve3HzvOfTgYwdgp0KAmYAqdDnq

-- Dumped from database version 18.4
-- Dumped by pg_dump version 18.4

-- Started on 2026-11-06 22:52:08

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 32871)
-- Name: proyecto; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA proyecto;


ALTER SCHEMA proyecto OWNER TO postgres;

--
-- TOC entry 899 (class 1247 OID 32873)
-- Name: correo; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.correo AS character varying(254)
	CONSTRAINT correo_check CHECK (((VALUE)::text ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$'::text));


ALTER DOMAIN public.correo OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 32875)
-- Name: administradores; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.administradores (
    id_usuario integer NOT NULL,
    adm_permiso character varying(100) NOT NULL,
    fec_alta date NOT NULL,
    puesto character varying(100)
);


ALTER TABLE proyecto.administradores OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 32881)
-- Name: carr_form; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.carr_form (
    id_formacion integer NOT NULL,
    id_carrera integer NOT NULL
);


ALTER TABLE proyecto.carr_form OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 32886)
-- Name: carreras; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.carreras (
    id_carrera integer NOT NULL,
    nom_carrera character varying(100),
    id_institucion integer
);


ALTER TABLE proyecto.carreras OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 32890)
-- Name: carreras_id_carrera_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.carreras_id_carrera_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.carreras_id_carrera_seq OWNER TO postgres;

--
-- TOC entry 5305 (class 0 OID 0)
-- Dependencies: 223
-- Name: carreras_id_carrera_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.carreras_id_carrera_seq OWNED BY proyecto.carreras.id_carrera;


--
-- TOC entry 224 (class 1259 OID 32891)
-- Name: ces; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.ces (
    id_ces integer NOT NULL,
    puesto character varying(50) NOT NULL
);


ALTER TABLE proyecto.ces OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 32896)
-- Name: ces_id_ces_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.ces_id_ces_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.ces_id_ces_seq OWNER TO postgres;

--
-- TOC entry 5306 (class 0 OID 0)
-- Dependencies: 225
-- Name: ces_id_ces_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.ces_id_ces_seq OWNED BY proyecto.ces.id_ces;


--
-- TOC entry 226 (class 1259 OID 32897)
-- Name: convocatorias; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.convocatorias (
    id_convocatoria integer NOT NULL,
    descripcion character varying(200),
    anio_convocatoria integer,
    cantidad_becas integer,
    tipo_beca character varying(100),
    estado character varying(100),
    fecha_cierre date,
    fecha_apertura date,
    id_rubrica integer NOT NULL,
    nombre character varying(100)
);


ALTER TABLE proyecto.convocatorias OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 32902)
-- Name: convocatorias_id_convocatoria_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.convocatorias_id_convocatoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.convocatorias_id_convocatoria_seq OWNER TO postgres;

--
-- TOC entry 5307 (class 0 OID 0)
-- Dependencies: 227
-- Name: convocatorias_id_convocatoria_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.convocatorias_id_convocatoria_seq OWNED BY proyecto.convocatorias.id_convocatoria;


--
-- TOC entry 228 (class 1259 OID 32903)
-- Name: criterios; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.criterios (
    id_criterio integer NOT NULL,
    nom_criterio character varying(30) NOT NULL,
    id_secrubrica integer NOT NULL
);


ALTER TABLE proyecto.criterios OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 32909)
-- Name: criterios_id_criterio_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.criterios_id_criterio_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.criterios_id_criterio_seq OWNER TO postgres;

--
-- TOC entry 5308 (class 0 OID 0)
-- Dependencies: 229
-- Name: criterios_id_criterio_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.criterios_id_criterio_seq OWNED BY proyecto.criterios.id_criterio;


--
-- TOC entry 230 (class 1259 OID 32910)
-- Name: depa_academicos; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.depa_academicos (
    id_departamento integer NOT NULL,
    nombre character varying(100)
);


ALTER TABLE proyecto.depa_academicos OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 32915)
-- Name: depa_academicos_id_departamento_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.depa_academicos_id_departamento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.depa_academicos_id_departamento_seq OWNER TO postgres;

--
-- TOC entry 5309 (class 0 OID 0)
-- Dependencies: 231
-- Name: depa_academicos_id_departamento_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.depa_academicos_id_departamento_seq OWNED BY proyecto.depa_academicos.id_departamento;


--
-- TOC entry 232 (class 1259 OID 32916)
-- Name: docu_adjuntos; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.docu_adjuntos (
    id_documento integer NOT NULL,
    id_postulacion integer NOT NULL,
    nom_documento character varying(100) NOT NULL,
    ruta_archivo character varying(200) NOT NULL,
    id_tipodocumento integer
);


ALTER TABLE proyecto.docu_adjuntos OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 32923)
-- Name: docu_adjuntos_id_documento_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.docu_adjuntos_id_documento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.docu_adjuntos_id_documento_seq OWNER TO postgres;

--
-- TOC entry 5310 (class 0 OID 0)
-- Dependencies: 233
-- Name: docu_adjuntos_id_documento_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.docu_adjuntos_id_documento_seq OWNED BY proyecto.docu_adjuntos.id_documento;


--
-- TOC entry 234 (class 1259 OID 32924)
-- Name: eva_externos; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.eva_externos (
    id_usuario integer NOT NULL,
    institucion character varying(100) NOT NULL
);


ALTER TABLE proyecto.eva_externos OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 32929)
-- Name: evaluaciones; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.evaluaciones (
    id_evaluacion integer NOT NULL,
    nombre character varying(100) NOT NULL,
    pun_total numeric(10,2),
    jui_global character varying(200),
    com_evaluador character varying(200),
    estado character varying(100) NOT NULL,
    id_usuario integer NOT NULL,
    id_postulacion integer NOT NULL
);


ALTER TABLE proyecto.evaluaciones OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 32939)
-- Name: formaciones; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.formaciones (
    id_formacion integer NOT NULL,
    nom_formacion character varying(50) NOT NULL,
    id_postulante integer
);


ALTER TABLE proyecto.formaciones OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 32944)
-- Name: formaciones_id_formacion_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.formaciones_id_formacion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.formaciones_id_formacion_seq OWNER TO postgres;

--
-- TOC entry 5311 (class 0 OID 0)
-- Dependencies: 237
-- Name: formaciones_id_formacion_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.formaciones_id_formacion_seq OWNED BY proyecto.formaciones.id_formacion;


--
-- TOC entry 238 (class 1259 OID 32945)
-- Name: incluyen; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.incluyen (
    id_tutor integer NOT NULL,
    id_postulacion integer NOT NULL,
    rol_tutor character varying(100) NOT NULL
);


ALTER TABLE proyecto.incluyen OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 32951)
-- Name: instituciones; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.instituciones (
    id_institucion integer NOT NULL,
    nom_institucion character varying(50) NOT NULL
);


ALTER TABLE proyecto.instituciones OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 32956)
-- Name: instituciones_id_institucion_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.instituciones_id_institucion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.instituciones_id_institucion_seq OWNER TO postgres;

--
-- TOC entry 5312 (class 0 OID 0)
-- Dependencies: 240
-- Name: instituciones_id_institucion_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.instituciones_id_institucion_seq OWNED BY proyecto.instituciones.id_institucion;


--
-- TOC entry 241 (class 1259 OID 32957)
-- Name: lista_prelacion; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.lista_prelacion (
    id_lista integer NOT NULL,
    vigencia date NOT NULL,
    cantidad_aprobados integer NOT NULL,
    posicion integer NOT NULL,
    observacion character varying(200),
    id_ces integer NOT NULL,
    id_convocatoria integer NOT NULL
);


ALTER TABLE proyecto.lista_prelacion OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 32966)
-- Name: lista_prelacion_id_lista_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.lista_prelacion_id_lista_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.lista_prelacion_id_lista_seq OWNER TO postgres;

--
-- TOC entry 5313 (class 0 OID 0)
-- Dependencies: 242
-- Name: lista_prelacion_id_lista_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.lista_prelacion_id_lista_seq OWNED BY proyecto.lista_prelacion.id_lista;


--
-- TOC entry 243 (class 1259 OID 32967)
-- Name: posg_form; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.posg_form (
    id_posgrado integer NOT NULL,
    id_formacion integer NOT NULL
);


ALTER TABLE proyecto.posg_form OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 32972)
-- Name: posgrados; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.posgrados (
    id_posgrado integer NOT NULL,
    nom_posgrado character varying(50),
    id_institucion integer
);


ALTER TABLE proyecto.posgrados OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 32976)
-- Name: posgrados_id_posgrado_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.posgrados_id_posgrado_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.posgrados_id_posgrado_seq OWNER TO postgres;

--
-- TOC entry 5314 (class 0 OID 0)
-- Dependencies: 245
-- Name: posgrados_id_posgrado_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.posgrados_id_posgrado_seq OWNED BY proyecto.posgrados.id_posgrado;


--
-- TOC entry 246 (class 1259 OID 32977)
-- Name: post_celulares; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.post_celulares (
    celular character varying(9) NOT NULL,
    id_postulante integer NOT NULL
);


ALTER TABLE proyecto.post_celulares OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 32982)
-- Name: postulaciones; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.postulaciones (
    id_postulacion integer NOT NULL,
    nombre character varying(100) NOT NULL,
    can_mes integer NOT NULL,
    linea_investigacion character varying(50) NOT NULL,
    fecha_solicitud date NOT NULL,
    observaciones_ces character varying(50),
    grupo_investigacion character varying(50) NOT NULL,
    otro_g_investigacion character varying(50),
    orden_final_ces character varying(50),
    id_departamento integer NOT NULL,
    id_postulante integer NOT NULL,
    id_sede integer NOT NULL,
    estado character varying(100),
    id_convocatoria integer
);


ALTER TABLE proyecto.postulaciones OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 32994)
-- Name: postulaciones_id_postulacion_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.postulaciones_id_postulacion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.postulaciones_id_postulacion_seq OWNER TO postgres;

--
-- TOC entry 5315 (class 0 OID 0)
-- Dependencies: 248
-- Name: postulaciones_id_postulacion_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.postulaciones_id_postulacion_seq OWNED BY proyecto.postulaciones.id_postulacion;


--
-- TOC entry 249 (class 1259 OID 32995)
-- Name: postulantes; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.postulantes (
    id_postulante integer NOT NULL,
    documento character varying(8),
    nombre character varying(100) NOT NULL,
    apellido character varying(100) NOT NULL,
    correo public.correo NOT NULL,
    nacionalidad character varying(100) NOT NULL,
    act_laboral character varying(100),
    es_doc_utec boolean,
    calle character varying(100),
    numero character varying(3),
    apartamento character varying(3),
    bis character varying(5),
    loc_procedencia character varying(100) NOT NULL,
    dep_procedencia character varying(100) NOT NULL,
    pai_procedencia character varying(100) NOT NULL
);


ALTER TABLE proyecto.postulantes OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 33009)
-- Name: postulantes_id_postulante_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.postulantes_id_postulante_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.postulantes_id_postulante_seq OWNER TO postgres;

--
-- TOC entry 5316 (class 0 OID 0)
-- Dependencies: 250
-- Name: postulantes_id_postulante_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.postulantes_id_postulante_seq OWNED BY proyecto.postulantes.id_postulante;


--
-- TOC entry 251 (class 1259 OID 33010)
-- Name: requieren; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.requieren (
    id_tipodocumento integer NOT NULL,
    id_convocatoria integer NOT NULL
);


ALTER TABLE proyecto.requieren OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 33015)
-- Name: rubricas; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.rubricas (
    id_rubrica integer NOT NULL,
    nombre character varying(50) NOT NULL
);


ALTER TABLE proyecto.rubricas OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 33020)
-- Name: rubricas_id_rubrica_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.rubricas_id_rubrica_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.rubricas_id_rubrica_seq OWNER TO postgres;

--
-- TOC entry 5317 (class 0 OID 0)
-- Dependencies: 253
-- Name: rubricas_id_rubrica_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.rubricas_id_rubrica_seq OWNED BY proyecto.rubricas.id_rubrica;


--
-- TOC entry 254 (class 1259 OID 33021)
-- Name: secciones_rubricas; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.secciones_rubricas (
    id_secrubrica integer NOT NULL,
    peso numeric(5,2) NOT NULL,
    id_rubrica integer NOT NULL,
    nombre character varying(100)
);


ALTER TABLE proyecto.secciones_rubricas OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 33027)
-- Name: secciones_rubricas_id_secrubrica_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.secciones_rubricas_id_secrubrica_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.secciones_rubricas_id_secrubrica_seq OWNER TO postgres;

--
-- TOC entry 5318 (class 0 OID 0)
-- Dependencies: 255
-- Name: secciones_rubricas_id_secrubrica_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.secciones_rubricas_id_secrubrica_seq OWNED BY proyecto.secciones_rubricas.id_secrubrica;


--
-- TOC entry 256 (class 1259 OID 33028)
-- Name: sedes; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.sedes (
    id_sede integer NOT NULL,
    nom_sede character varying(50) NOT NULL
);


ALTER TABLE proyecto.sedes OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 33033)
-- Name: sedes_id_sede_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.sedes_id_sede_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.sedes_id_sede_seq OWNER TO postgres;

--
-- TOC entry 5319 (class 0 OID 0)
-- Dependencies: 257
-- Name: sedes_id_sede_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.sedes_id_sede_seq OWNED BY proyecto.sedes.id_sede;


--
-- TOC entry 258 (class 1259 OID 33034)
-- Name: son_asignadas; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.son_asignadas (
    id_postulacion integer NOT NULL,
    id_usuario integer NOT NULL,
    fec_ace_acu_confidencialidad date,
    fec_ace_aus_confidencialidad date
);


ALTER TABLE proyecto.son_asignadas OWNER TO postgres;

--
-- TOC entry 259 (class 1259 OID 33041)
-- Name: tipo_documentos; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.tipo_documentos (
    id_tipodocumento integer NOT NULL,
    nom_tipo_doc character varying(100) NOT NULL
);


ALTER TABLE proyecto.tipo_documentos OWNER TO postgres;

--
-- TOC entry 260 (class 1259 OID 33048)
-- Name: tipo_documentos_id_tipodocumento_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.tipo_documentos_id_tipodocumento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.tipo_documentos_id_tipodocumento_seq OWNER TO postgres;

--
-- TOC entry 5320 (class 0 OID 0)
-- Dependencies: 260
-- Name: tipo_documentos_id_tipodocumento_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.tipo_documentos_id_tipodocumento_seq OWNED BY proyecto.tipo_documentos.id_tipodocumento;


--
-- TOC entry 261 (class 1259 OID 33049)
-- Name: tut_utec; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.tut_utec (
    id_tutor integer NOT NULL,
    cor_utec character varying(100) NOT NULL,
    nombre character varying(100) NOT NULL,
    apellido character varying(100) NOT NULL,
    dep_area character varying(100) NOT NULL
);


ALTER TABLE proyecto.tut_utec OWNER TO postgres;

--
-- TOC entry 262 (class 1259 OID 33057)
-- Name: tut_utec_id_tutor_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.tut_utec_id_tutor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.tut_utec_id_tutor_seq OWNER TO postgres;

--
-- TOC entry 5321 (class 0 OID 0)
-- Dependencies: 262
-- Name: tut_utec_id_tutor_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.tut_utec_id_tutor_seq OWNED BY proyecto.tut_utec.id_tutor;


--
-- TOC entry 263 (class 1259 OID 33058)
-- Name: usua_celulares; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.usua_celulares (
    celular character varying(9) NOT NULL,
    id_usuario integer NOT NULL
);


ALTER TABLE proyecto.usua_celulares OWNER TO postgres;

--
-- TOC entry 264 (class 1259 OID 33063)
-- Name: usuarios; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.usuarios (
    id_usuario integer NOT NULL,
    correo public.correo NOT NULL,
    nombre character varying(50) NOT NULL,
    apellido character varying(50) NOT NULL,
    calle character varying(50) NOT NULL,
    nacionalidad character varying(50) NOT NULL,
    ci character varying(50) NOT NULL,
    contrasena character varying(50) CONSTRAINT "usuarios_contraseña_not_null" NOT NULL,
    estado character varying(50) NOT NULL,
    rol character varying(50) NOT NULL,
    numero integer
);


ALTER TABLE proyecto.usuarios OWNER TO postgres;

--
-- TOC entry 265 (class 1259 OID 33078)
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE; Schema: proyecto; Owner: postgres
--

CREATE SEQUENCE proyecto.usuarios_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE proyecto.usuarios_id_usuario_seq OWNER TO postgres;

--
-- TOC entry 5322 (class 0 OID 0)
-- Dependencies: 265
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: proyecto; Owner: postgres
--

ALTER SEQUENCE proyecto.usuarios_id_usuario_seq OWNED BY proyecto.usuarios.id_usuario;


--
-- TOC entry 266 (class 1259 OID 33079)
-- Name: valoraciones; Type: TABLE; Schema: proyecto; Owner: postgres
--

CREATE TABLE proyecto.valoraciones (
    id_criterio integer NOT NULL,
    puntaje numeric(10,2) NOT NULL,
    id_evaluacion integer NOT NULL,
    id_usuario integer NOT NULL,
    id_postulacion integer NOT NULL
);


ALTER TABLE proyecto.valoraciones OWNER TO postgres;

--
-- TOC entry 4990 (class 2604 OID 33087)
-- Name: carreras id_carrera; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.carreras ALTER COLUMN id_carrera SET DEFAULT nextval('proyecto.carreras_id_carrera_seq'::regclass);


--
-- TOC entry 4991 (class 2604 OID 33088)
-- Name: ces id_ces; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.ces ALTER COLUMN id_ces SET DEFAULT nextval('proyecto.ces_id_ces_seq'::regclass);


--
-- TOC entry 4992 (class 2604 OID 33089)
-- Name: convocatorias id_convocatoria; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.convocatorias ALTER COLUMN id_convocatoria SET DEFAULT nextval('proyecto.convocatorias_id_convocatoria_seq'::regclass);


--
-- TOC entry 4993 (class 2604 OID 33090)
-- Name: criterios id_criterio; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.criterios ALTER COLUMN id_criterio SET DEFAULT nextval('proyecto.criterios_id_criterio_seq'::regclass);


--
-- TOC entry 4994 (class 2604 OID 33091)
-- Name: depa_academicos id_departamento; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.depa_academicos ALTER COLUMN id_departamento SET DEFAULT nextval('proyecto.depa_academicos_id_departamento_seq'::regclass);


--
-- TOC entry 4995 (class 2604 OID 33347)
-- Name: docu_adjuntos id_documento; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.docu_adjuntos ALTER COLUMN id_documento SET DEFAULT nextval('proyecto.docu_adjuntos_id_documento_seq'::regclass);


--
-- TOC entry 4996 (class 2604 OID 33093)
-- Name: formaciones id_formacion; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.formaciones ALTER COLUMN id_formacion SET DEFAULT nextval('proyecto.formaciones_id_formacion_seq'::regclass);


--
-- TOC entry 4997 (class 2604 OID 33094)
-- Name: instituciones id_institucion; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.instituciones ALTER COLUMN id_institucion SET DEFAULT nextval('proyecto.instituciones_id_institucion_seq'::regclass);


--
-- TOC entry 4998 (class 2604 OID 33095)
-- Name: lista_prelacion id_lista; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.lista_prelacion ALTER COLUMN id_lista SET DEFAULT nextval('proyecto.lista_prelacion_id_lista_seq'::regclass);


--
-- TOC entry 4999 (class 2604 OID 33096)
-- Name: posgrados id_posgrado; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.posgrados ALTER COLUMN id_posgrado SET DEFAULT nextval('proyecto.posgrados_id_posgrado_seq'::regclass);


--
-- TOC entry 5000 (class 2604 OID 33097)
-- Name: postulaciones id_postulacion; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.postulaciones ALTER COLUMN id_postulacion SET DEFAULT nextval('proyecto.postulaciones_id_postulacion_seq'::regclass);


--
-- TOC entry 5001 (class 2604 OID 33098)
-- Name: postulantes id_postulante; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.postulantes ALTER COLUMN id_postulante SET DEFAULT nextval('proyecto.postulantes_id_postulante_seq'::regclass);


--
-- TOC entry 5002 (class 2604 OID 33099)
-- Name: rubricas id_rubrica; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.rubricas ALTER COLUMN id_rubrica SET DEFAULT nextval('proyecto.rubricas_id_rubrica_seq'::regclass);


--
-- TOC entry 5003 (class 2604 OID 33100)
-- Name: secciones_rubricas id_secrubrica; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.secciones_rubricas ALTER COLUMN id_secrubrica SET DEFAULT nextval('proyecto.secciones_rubricas_id_secrubrica_seq'::regclass);


--
-- TOC entry 5004 (class 2604 OID 33101)
-- Name: sedes id_sede; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.sedes ALTER COLUMN id_sede SET DEFAULT nextval('proyecto.sedes_id_sede_seq'::regclass);


--
-- TOC entry 5005 (class 2604 OID 33102)
-- Name: tipo_documentos id_tipodocumento; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.tipo_documentos ALTER COLUMN id_tipodocumento SET DEFAULT nextval('proyecto.tipo_documentos_id_tipodocumento_seq'::regclass);


--
-- TOC entry 5006 (class 2604 OID 33103)
-- Name: tut_utec id_tutor; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.tut_utec ALTER COLUMN id_tutor SET DEFAULT nextval('proyecto.tut_utec_id_tutor_seq'::regclass);


--
-- TOC entry 5007 (class 2604 OID 33104)
-- Name: usuarios id_usuario; Type: DEFAULT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.usuarios ALTER COLUMN id_usuario SET DEFAULT nextval('proyecto.usuarios_id_usuario_seq'::regclass);


--
-- TOC entry 5253 (class 0 OID 32875)
-- Dependencies: 220
-- Data for Name: administradores; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.administradores (id_usuario, adm_permiso, fec_alta, puesto) VALUES (3, 'admin', '2026-10-03', 'ultramegasenior');
INSERT INTO proyecto.administradores (id_usuario, adm_permiso, fec_alta, puesto) VALUES (6, 'admin', '2026-10-10', 'administracion');


--
-- TOC entry 5254 (class 0 OID 32881)
-- Dependencies: 221
-- Data for Name: carr_form; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.carr_form (id_formacion, id_carrera) VALUES (4, 6);
INSERT INTO proyecto.carr_form (id_formacion, id_carrera) VALUES (6, 8);
INSERT INTO proyecto.carr_form (id_formacion, id_carrera) VALUES (7, 9);


--
-- TOC entry 5255 (class 0 OID 32886)
-- Dependencies: 222
-- Data for Name: carreras; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.carreras (id_carrera, nom_carrera, id_institucion) VALUES (2, 'LTI', 2);
INSERT INTO proyecto.carreras (id_carrera, nom_carrera, id_institucion) VALUES (3, 'LTI', 2);
INSERT INTO proyecto.carreras (id_carrera, nom_carrera, id_institucion) VALUES (4, 'LTI', 2);
INSERT INTO proyecto.carreras (id_carrera, nom_carrera, id_institucion) VALUES (5, 'LTI', 2);
INSERT INTO proyecto.carreras (id_carrera, nom_carrera, id_institucion) VALUES (6, 'LTI 2026', 2);
INSERT INTO proyecto.carreras (id_carrera, nom_carrera, id_institucion) VALUES (7, 'f', 2);
INSERT INTO proyecto.carreras (id_carrera, nom_carrera, id_institucion) VALUES (8, 'jazzzz', 2);
INSERT INTO proyecto.carreras (id_carrera, nom_carrera, id_institucion) VALUES (9, 'quesos artesanales', 2);
INSERT INTO proyecto.carreras (id_carrera, nom_carrera, id_institucion) VALUES (10, 'Ingeniería en Sistemas', 2);


--
-- TOC entry 5257 (class 0 OID 32891)
-- Dependencies: 224
-- Data for Name: ces; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--



--
-- TOC entry 5259 (class 0 OID 32897)
-- Dependencies: 226
-- Data for Name: convocatorias; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.convocatorias (id_convocatoria, descripcion, anio_convocatoria, cantidad_becas, tipo_beca, estado, fecha_cierre, fecha_apertura, id_rubrica, nombre) VALUES (2, 'fue', 2026, 2, 'investigacion', 'completada', '2026-10-08', '2026-10-10', 5, 'aEliminar');
INSERT INTO proyecto.convocatorias (id_convocatoria, descripcion, anio_convocatoria, cantidad_becas, tipo_beca, estado, fecha_cierre, fecha_apertura, id_rubrica, nombre) VALUES (1, 'fdshfewufeuivi1', 2026, 34, 'posgrado', 'activo', '2027-10-18', '2027-10-10', 8, 'canvocatoria ejemplo 1');


--
-- TOC entry 5261 (class 0 OID 32903)
-- Dependencies: 228
-- Data for Name: criterios; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.criterios (id_criterio, nom_criterio, id_secrubrica) VALUES (5, 'mnom1', 19);
INSERT INTO proyecto.criterios (id_criterio, nom_criterio, id_secrubrica) VALUES (6, 'nom2', 22);
INSERT INTO proyecto.criterios (id_criterio, nom_criterio, id_secrubrica) VALUES (7, 'nom3', 23);
INSERT INTO proyecto.criterios (id_criterio, nom_criterio, id_secrubrica) VALUES (8, 'nom4', 24);


--
-- TOC entry 5263 (class 0 OID 32910)
-- Dependencies: 230
-- Data for Name: depa_academicos; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.depa_academicos (id_departamento, nombre) VALUES (2, 'departamento1');


--
-- TOC entry 5265 (class 0 OID 32916)
-- Dependencies: 232
-- Data for Name: docu_adjuntos; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--



--
-- TOC entry 5267 (class 0 OID 32924)
-- Dependencies: 234
-- Data for Name: eva_externos; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.eva_externos (id_usuario, institucion) VALUES (5, 'fray bentos utec');
INSERT INTO proyecto.eva_externos (id_usuario, institucion) VALUES (7, 'fray bentos');
INSERT INTO proyecto.eva_externos (id_usuario, institucion) VALUES (8, 'utec');
INSERT INTO proyecto.eva_externos (id_usuario, institucion) VALUES (9, 'utec');


--
-- TOC entry 5268 (class 0 OID 32929)
-- Dependencies: 235
-- Data for Name: evaluaciones; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.evaluaciones (id_evaluacion, nombre, pun_total, jui_global, com_evaluador, estado, id_usuario, id_postulacion) VALUES (2, 'evaluacion2', 11.00, 'jui2', 'com2', 'En evaluación', 8, 1);
INSERT INTO proyecto.evaluaciones (id_evaluacion, nombre, pun_total, jui_global, com_evaluador, estado, id_usuario, id_postulacion) VALUES (1, 'evaluacion1', 10.00, 'juicio1', 'com1', 'En evaluación', 8, 1);
INSERT INTO proyecto.evaluaciones (id_evaluacion, nombre, pun_total, jui_global, com_evaluador, estado, id_usuario, id_postulacion) VALUES (3, 'evPrueba', 10.00, 'aa', 'aa', 'En evaluación', 9, 1);
INSERT INTO proyecto.evaluaciones (id_evaluacion, nombre, pun_total, jui_global, com_evaluador, estado, id_usuario, id_postulacion) VALUES (4, 'Evaluación de Postulación', 3.50, 'recom2', 'com1', 'Completada', 5, 3);


--
-- TOC entry 5269 (class 0 OID 32939)
-- Dependencies: 236
-- Data for Name: formaciones; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.formaciones (id_formacion, nom_formacion, id_postulante) VALUES (3, 'TI', 2);
INSERT INTO proyecto.formaciones (id_formacion, nom_formacion, id_postulante) VALUES (4, 'TI', 3);
INSERT INTO proyecto.formaciones (id_formacion, nom_formacion, id_postulante) VALUES (6, 'lti', 6);
INSERT INTO proyecto.formaciones (id_formacion, nom_formacion, id_postulante) VALUES (7, 'cocina', 7);


--
-- TOC entry 5271 (class 0 OID 32945)
-- Dependencies: 238
-- Data for Name: incluyen; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--



--
-- TOC entry 5272 (class 0 OID 32951)
-- Dependencies: 239
-- Data for Name: instituciones; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.instituciones (id_institucion, nom_institucion) VALUES (2, 'UTEC');


--
-- TOC entry 5274 (class 0 OID 32957)
-- Dependencies: 241
-- Data for Name: lista_prelacion; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--



--
-- TOC entry 5276 (class 0 OID 32967)
-- Dependencies: 243
-- Data for Name: posg_form; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.posg_form (id_posgrado, id_formacion) VALUES (4, 3);


--
-- TOC entry 5277 (class 0 OID 32972)
-- Dependencies: 244
-- Data for Name: posgrados; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.posgrados (id_posgrado, nom_posgrado, id_institucion) VALUES (1, 'Master en Java', 2);
INSERT INTO proyecto.posgrados (id_posgrado, nom_posgrado, id_institucion) VALUES (2, 'Java Senior pro', 2);
INSERT INTO proyecto.posgrados (id_posgrado, nom_posgrado, id_institucion) VALUES (3, 'Java Senior', 2);
INSERT INTO proyecto.posgrados (id_posgrado, nom_posgrado, id_institucion) VALUES (4, 'Java Master pro', 2);


--
-- TOC entry 5279 (class 0 OID 32977)
-- Dependencies: 246
-- Data for Name: post_celulares; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.post_celulares (celular, id_postulante) VALUES ('098321321', 2);
INSERT INTO proyecto.post_celulares (celular, id_postulante) VALUES ('098123123', 3);
INSERT INTO proyecto.post_celulares (celular, id_postulante) VALUES ('09823', 6);
INSERT INTO proyecto.post_celulares (celular, id_postulante) VALUES ('09826382', 7);


--
-- TOC entry 5280 (class 0 OID 32982)
-- Dependencies: 247
-- Data for Name: postulaciones; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.postulaciones (id_postulacion, nombre, can_mes, linea_investigacion, fecha_solicitud, observaciones_ces, grupo_investigacion, otro_g_investigacion, orden_final_ces, id_departamento, id_postulante, id_sede, estado, id_convocatoria) VALUES (2, 'postulacion2', 12, 'lti', '2026-11-03', 'a', 'g1', 'g2', 'aaa', 2, 3, 2, 'En evaluación', 1);
INSERT INTO proyecto.postulaciones (id_postulacion, nombre, can_mes, linea_investigacion, fecha_solicitud, observaciones_ces, grupo_investigacion, otro_g_investigacion, orden_final_ces, id_departamento, id_postulante, id_sede, estado, id_convocatoria) VALUES (1, 'pos1', 12, 'lti', '2026-10-21', 'ob1', 'grupo1', 'grupo2', 'or2', 2, 2, 2, 'En evaluación', NULL);
INSERT INTO proyecto.postulaciones (id_postulacion, nombre, can_mes, linea_investigacion, fecha_solicitud, observaciones_ces, grupo_investigacion, otro_g_investigacion, orden_final_ces, id_departamento, id_postulante, id_sede, estado, id_convocatoria) VALUES (3, 'postulacionTest1', 12, 'cocina', '2026-11-03', 'fue', 'c1', 'c2', 'ordenTest1', 2, 7, 2, 'En evaluación', 1);


--
-- TOC entry 5282 (class 0 OID 32995)
-- Dependencies: 249
-- Data for Name: postulantes; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.postulantes (id_postulante, documento, nombre, apellido, correo, nacionalidad, act_laboral, es_doc_utec, calle, numero, apartamento, bis, loc_procedencia, dep_procedencia, pai_procedencia) VALUES (2, '34554656', 'juan', 'maccio', 'juan@gmail.com', 'uruguayo', 'profesor', true, '3', '4', '5', '6', 'Montevideo', 'Montevideo', 'Uruguay');
INSERT INTO proyecto.postulantes (id_postulante, documento, nombre, apellido, correo, nacionalidad, act_laboral, es_doc_utec, calle, numero, apartamento, bis, loc_procedencia, dep_procedencia, pai_procedencia) VALUES (3, '3245546', 'gael', 'russo', 'gael@gmail.com', 'uruguayo', 'laburador', false, '2', '3', '4', '5', 'FB', 'RN', 'Uruguay');
INSERT INTO proyecto.postulantes (id_postulante, documento, nombre, apellido, correo, nacionalidad, act_laboral, es_doc_utec, calle, numero, apartamento, bis, loc_procedencia, dep_procedencia, pai_procedencia) VALUES (6, '', '', '', 'g@gmail.com', 'uru', 'si', false, '12', '12', '12', '12', 's', 's', 'd');
INSERT INTO proyecto.postulantes (id_postulante, documento, nombre, apellido, correo, nacionalidad, act_laboral, es_doc_utec, calle, numero, apartamento, bis, loc_procedencia, dep_procedencia, pai_procedencia) VALUES (7, '3849349', 'test1', 'aptest1', 'test@gmail.com', 'uruguayo', 'laburante nato', false, '12', '13', '14', '15', 'fb', 'rn', 'uruguay');


--
-- TOC entry 5284 (class 0 OID 33010)
-- Dependencies: 251
-- Data for Name: requieren; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.requieren (id_tipodocumento, id_convocatoria) VALUES (1, 1);
INSERT INTO proyecto.requieren (id_tipodocumento, id_convocatoria) VALUES (2, 2);


--
-- TOC entry 5285 (class 0 OID 33015)
-- Dependencies: 252
-- Data for Name: rubricas; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.rubricas (id_rubrica, nombre) VALUES (3, 'rubrica1');
INSERT INTO proyecto.rubricas (id_rubrica, nombre) VALUES (5, 'rubrica2');
INSERT INTO proyecto.rubricas (id_rubrica, nombre) VALUES (6, 'rubricaPrueba');
INSERT INTO proyecto.rubricas (id_rubrica, nombre) VALUES (7, 'rubricaTEST1');
INSERT INTO proyecto.rubricas (id_rubrica, nombre) VALUES (8, 'rubtest1');


--
-- TOC entry 5287 (class 0 OID 33021)
-- Dependencies: 254
-- Data for Name: secciones_rubricas; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (1, 10.00, 3, 'seccion1');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (2, 20.00, 3, 'seccion2');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (3, 30.00, 3, 'seccion3');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (6, 10.00, 3, 'seccion 1 de la rubrica 1');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (7, 15.00, 6, 'Antecedentes del postulante');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (8, 15.00, 6, 'Antecedentes del tutor y grupo receptor');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (9, 15.00, 6, 'Descripción del plan de trabajo');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (10, 15.00, 6, 'Cronograma de actividades');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (11, 20.00, 6, 'Aporte al desarrollo del postulante');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (12, 20.00, 6, 'Aporte al grupo receptor');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (13, 15.00, 7, 'Antecedentes del postulante');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (14, 15.00, 7, 'Antecedentes del tutor y grupo receptor');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (15, 15.00, 7, 'Descripción del plan de trabajo');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (16, 15.00, 7, 'Cronograma de actividades');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (17, 20.00, 7, 'Aporte al desarrollo del postulante');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (18, 20.00, 7, 'Aporte al grupo receptor');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (19, 15.00, 8, 'Antecedentes del postulante');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (20, 15.00, 8, 'Antecedentes del tutor y grupo receptor');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (21, 15.00, 8, 'Descripción del plan de trabajo');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (22, 15.00, 8, 'Cronograma de actividades');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (23, 20.00, 8, 'Aporte al desarrollo del postulante');
INSERT INTO proyecto.secciones_rubricas (id_secrubrica, peso, id_rubrica, nombre) VALUES (24, 20.00, 8, 'Aporte al grupo receptor');


--
-- TOC entry 5289 (class 0 OID 33028)
-- Dependencies: 256
-- Data for Name: sedes; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.sedes (id_sede, nom_sede) VALUES (2, 'fray bentos sede');


--
-- TOC entry 5291 (class 0 OID 33034)
-- Dependencies: 258
-- Data for Name: son_asignadas; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.son_asignadas (id_postulacion, id_usuario, fec_ace_acu_confidencialidad, fec_ace_aus_confidencialidad) VALUES (1, 5, NULL, NULL);
INSERT INTO proyecto.son_asignadas (id_postulacion, id_usuario, fec_ace_acu_confidencialidad, fec_ace_aus_confidencialidad) VALUES (2, 5, NULL, NULL);
INSERT INTO proyecto.son_asignadas (id_postulacion, id_usuario, fec_ace_acu_confidencialidad, fec_ace_aus_confidencialidad) VALUES (2, 8, NULL, NULL);
INSERT INTO proyecto.son_asignadas (id_postulacion, id_usuario, fec_ace_acu_confidencialidad, fec_ace_aus_confidencialidad) VALUES (1, 9, NULL, NULL);
INSERT INTO proyecto.son_asignadas (id_postulacion, id_usuario, fec_ace_acu_confidencialidad, fec_ace_aus_confidencialidad) VALUES (3, 5, '2026-11-04', '2026-11-04');


--
-- TOC entry 5292 (class 0 OID 33041)
-- Dependencies: 259
-- Data for Name: tipo_documentos; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.tipo_documentos (id_tipodocumento, nom_tipo_doc) VALUES (1, 'docEditado1');
INSERT INTO proyecto.tipo_documentos (id_tipodocumento, nom_tipo_doc) VALUES (2, 'doc1');


--
-- TOC entry 5294 (class 0 OID 33049)
-- Dependencies: 261
-- Data for Name: tut_utec; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--



--
-- TOC entry 5296 (class 0 OID 33058)
-- Dependencies: 263
-- Data for Name: usua_celulares; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--



--
-- TOC entry 5297 (class 0 OID 33063)
-- Dependencies: 264
-- Data for Name: usuarios; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.usuarios (id_usuario, correo, nombre, apellido, calle, nacionalidad, ci, contrasena, estado, rol, numero) VALUES (1, 'fran@gmail.com', 'francisco', 'barbosa', 'uruguay', 'uruguayo', '12345678', '1ae787eb4f867f', 'inactivo', 'evaluador', NULL);
INSERT INTO proyecto.usuarios (id_usuario, correo, nombre, apellido, calle, nacionalidad, ci, contrasena, estado, rol, numero) VALUES (6, 'prueba@gmail.com', 'prueba', 'a', 'uruguay', 'uruguayo', '12334587', '1ae787eb4f867f', 'activo', 'administrador', NULL);
INSERT INTO proyecto.usuarios (id_usuario, correo, nombre, apellido, calle, nacionalidad, ci, contrasena, estado, rol, numero) VALUES (7, 'f@gmail.com', 'prueba2', 'p', 'uruguay', 'uruguayo', '12345678', '1ae787eb4f867f', 'activo', 'evaluador', NULL);
INSERT INTO proyecto.usuarios (id_usuario, correo, nombre, apellido, calle, nacionalidad, ci, contrasena, estado, rol, numero) VALUES (8, 'ev@gmail.com', 'evaluado1', 'apellid1', '3', 'uruguayo', '547568', '1ae787eb4f867f', 'activo', 'evaluador', NULL);
INSERT INTO proyecto.usuarios (id_usuario, correo, nombre, apellido, calle, nacionalidad, ci, contrasena, estado, rol, numero) VALUES (9, 'ev2@gmail.com', 'evaludo2', 'ap2', '3', 'uruguayo', '6568673', '1ae787eb4f867f', 'activo', 'evaluador', NULL);
INSERT INTO proyecto.usuarios (id_usuario, correo, nombre, apellido, calle, nacionalidad, ci, contrasena, estado, rol, numero) VALUES (5, 'mateo@gmail.com', 'Mateo', 'Rodriguez', 'mercedes', 'uruguayo', '12345678', '6527dd1705a', 'activo', 'evaluador', NULL);
INSERT INTO proyecto.usuarios (id_usuario, correo, nombre, apellido, calle, nacionalidad, ci, contrasena, estado, rol, numero) VALUES (3, 'gael.russo@gmail.com', 'gael', 'russo', 'fray bentos', 'uruguayo', '78945632', '1ae4e937eef629', 'activo', 'administrador', 0);


--
-- TOC entry 5299 (class 0 OID 33079)
-- Dependencies: 266
-- Data for Name: valoraciones; Type: TABLE DATA; Schema: proyecto; Owner: postgres
--

INSERT INTO proyecto.valoraciones (id_criterio, puntaje, id_evaluacion, id_usuario, id_postulacion) VALUES (5, 5.00, 4, 5, 3);
INSERT INTO proyecto.valoraciones (id_criterio, puntaje, id_evaluacion, id_usuario, id_postulacion) VALUES (6, 3.00, 4, 5, 3);
INSERT INTO proyecto.valoraciones (id_criterio, puntaje, id_evaluacion, id_usuario, id_postulacion) VALUES (7, 4.00, 4, 5, 3);
INSERT INTO proyecto.valoraciones (id_criterio, puntaje, id_evaluacion, id_usuario, id_postulacion) VALUES (8, 5.00, 4, 5, 3);


--
-- TOC entry 5323 (class 0 OID 0)
-- Dependencies: 223
-- Name: carreras_id_carrera_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.carreras_id_carrera_seq', 10, true);


--
-- TOC entry 5324 (class 0 OID 0)
-- Dependencies: 225
-- Name: ces_id_ces_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.ces_id_ces_seq', 1, false);


--
-- TOC entry 5325 (class 0 OID 0)
-- Dependencies: 227
-- Name: convocatorias_id_convocatoria_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.convocatorias_id_convocatoria_seq', 2, true);


--
-- TOC entry 5326 (class 0 OID 0)
-- Dependencies: 229
-- Name: criterios_id_criterio_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.criterios_id_criterio_seq', 8, true);


--
-- TOC entry 5327 (class 0 OID 0)
-- Dependencies: 231
-- Name: depa_academicos_id_departamento_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.depa_academicos_id_departamento_seq', 2, true);


--
-- TOC entry 5328 (class 0 OID 0)
-- Dependencies: 233
-- Name: docu_adjuntos_id_documento_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.docu_adjuntos_id_documento_seq', 1, false);


--
-- TOC entry 5329 (class 0 OID 0)
-- Dependencies: 237
-- Name: formaciones_id_formacion_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.formaciones_id_formacion_seq', 8, true);


--
-- TOC entry 5330 (class 0 OID 0)
-- Dependencies: 240
-- Name: instituciones_id_institucion_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.instituciones_id_institucion_seq', 2, true);


--
-- TOC entry 5331 (class 0 OID 0)
-- Dependencies: 242
-- Name: lista_prelacion_id_lista_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.lista_prelacion_id_lista_seq', 1, false);


--
-- TOC entry 5332 (class 0 OID 0)
-- Dependencies: 245
-- Name: posgrados_id_posgrado_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.posgrados_id_posgrado_seq', 4, true);


--
-- TOC entry 5333 (class 0 OID 0)
-- Dependencies: 248
-- Name: postulaciones_id_postulacion_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.postulaciones_id_postulacion_seq', 3, true);


--
-- TOC entry 5334 (class 0 OID 0)
-- Dependencies: 250
-- Name: postulantes_id_postulante_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.postulantes_id_postulante_seq', 8, true);


--
-- TOC entry 5335 (class 0 OID 0)
-- Dependencies: 253
-- Name: rubricas_id_rubrica_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.rubricas_id_rubrica_seq', 8, true);


--
-- TOC entry 5336 (class 0 OID 0)
-- Dependencies: 255
-- Name: secciones_rubricas_id_secrubrica_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.secciones_rubricas_id_secrubrica_seq', 24, true);


--
-- TOC entry 5337 (class 0 OID 0)
-- Dependencies: 257
-- Name: sedes_id_sede_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.sedes_id_sede_seq', 2, true);


--
-- TOC entry 5338 (class 0 OID 0)
-- Dependencies: 260
-- Name: tipo_documentos_id_tipodocumento_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.tipo_documentos_id_tipodocumento_seq', 2, true);


--
-- TOC entry 5339 (class 0 OID 0)
-- Dependencies: 262
-- Name: tut_utec_id_tutor_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.tut_utec_id_tutor_seq', 1, true);


--
-- TOC entry 5340 (class 0 OID 0)
-- Dependencies: 265
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE SET; Schema: proyecto; Owner: postgres
--

SELECT pg_catalog.setval('proyecto.usuarios_id_usuario_seq', 9, true);


--
-- TOC entry 5009 (class 2606 OID 33106)
-- Name: administradores administradores_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.administradores
    ADD CONSTRAINT administradores_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 5011 (class 2606 OID 33108)
-- Name: carr_form carr_form_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.carr_form
    ADD CONSTRAINT carr_form_pkey PRIMARY KEY (id_formacion, id_carrera);


--
-- TOC entry 5013 (class 2606 OID 33110)
-- Name: carreras carreras_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.carreras
    ADD CONSTRAINT carreras_pkey PRIMARY KEY (id_carrera);


--
-- TOC entry 5015 (class 2606 OID 33112)
-- Name: ces ces_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.ces
    ADD CONSTRAINT ces_pkey PRIMARY KEY (id_ces);


--
-- TOC entry 5017 (class 2606 OID 33114)
-- Name: convocatorias convocatorias_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.convocatorias
    ADD CONSTRAINT convocatorias_pkey PRIMARY KEY (id_convocatoria);


--
-- TOC entry 5019 (class 2606 OID 33116)
-- Name: criterios criterios_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.criterios
    ADD CONSTRAINT criterios_pkey PRIMARY KEY (id_criterio);


--
-- TOC entry 5021 (class 2606 OID 33118)
-- Name: depa_academicos depa_academicos_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.depa_academicos
    ADD CONSTRAINT depa_academicos_pkey PRIMARY KEY (id_departamento);


--
-- TOC entry 5023 (class 2606 OID 33349)
-- Name: docu_adjuntos docu_adjuntos_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.docu_adjuntos
    ADD CONSTRAINT docu_adjuntos_pkey PRIMARY KEY (id_documento);


--
-- TOC entry 5025 (class 2606 OID 33122)
-- Name: eva_externos eva_externos_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.eva_externos
    ADD CONSTRAINT eva_externos_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 5027 (class 2606 OID 33124)
-- Name: evaluaciones evaluaciones_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.evaluaciones
    ADD CONSTRAINT evaluaciones_pkey PRIMARY KEY (id_evaluacion, id_usuario, id_postulacion);


--
-- TOC entry 5029 (class 2606 OID 33126)
-- Name: formaciones formaciones_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.formaciones
    ADD CONSTRAINT formaciones_pkey PRIMARY KEY (id_formacion);


--
-- TOC entry 5031 (class 2606 OID 33128)
-- Name: incluyen incluyen_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.incluyen
    ADD CONSTRAINT incluyen_pkey PRIMARY KEY (id_tutor, id_postulacion);


--
-- TOC entry 5033 (class 2606 OID 33130)
-- Name: instituciones instituciones_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.instituciones
    ADD CONSTRAINT instituciones_pkey PRIMARY KEY (id_institucion);


--
-- TOC entry 5037 (class 2606 OID 33132)
-- Name: lista_prelacion lista_prelacion_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.lista_prelacion
    ADD CONSTRAINT lista_prelacion_pkey PRIMARY KEY (id_lista);


--
-- TOC entry 5039 (class 2606 OID 33134)
-- Name: posg_form posg_form_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.posg_form
    ADD CONSTRAINT posg_form_pkey PRIMARY KEY (id_formacion, id_posgrado);


--
-- TOC entry 5041 (class 2606 OID 33136)
-- Name: posgrados posgrados_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.posgrados
    ADD CONSTRAINT posgrados_pkey PRIMARY KEY (id_posgrado);


--
-- TOC entry 5043 (class 2606 OID 33138)
-- Name: post_celulares post_celulares_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.post_celulares
    ADD CONSTRAINT post_celulares_pkey PRIMARY KEY (celular, id_postulante);


--
-- TOC entry 5045 (class 2606 OID 33140)
-- Name: postulaciones postulaciones_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.postulaciones
    ADD CONSTRAINT postulaciones_pkey PRIMARY KEY (id_postulacion);


--
-- TOC entry 5047 (class 2606 OID 33142)
-- Name: postulantes postulantes_correo_key; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.postulantes
    ADD CONSTRAINT postulantes_correo_key UNIQUE (correo);


--
-- TOC entry 5049 (class 2606 OID 33144)
-- Name: postulantes postulantes_documento_key; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.postulantes
    ADD CONSTRAINT postulantes_documento_key UNIQUE (documento);


--
-- TOC entry 5051 (class 2606 OID 33146)
-- Name: postulantes postulantes_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.postulantes
    ADD CONSTRAINT postulantes_pkey PRIMARY KEY (id_postulante);


--
-- TOC entry 5053 (class 2606 OID 33148)
-- Name: requieren requieren_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.requieren
    ADD CONSTRAINT requieren_pkey PRIMARY KEY (id_tipodocumento, id_convocatoria);


--
-- TOC entry 5055 (class 2606 OID 33150)
-- Name: rubricas rubricas_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.rubricas
    ADD CONSTRAINT rubricas_pkey PRIMARY KEY (id_rubrica);


--
-- TOC entry 5057 (class 2606 OID 33152)
-- Name: secciones_rubricas secciones_rubricas_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.secciones_rubricas
    ADD CONSTRAINT secciones_rubricas_pkey PRIMARY KEY (id_secrubrica);


--
-- TOC entry 5059 (class 2606 OID 33154)
-- Name: sedes sedes_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.sedes
    ADD CONSTRAINT sedes_pkey PRIMARY KEY (id_sede);


--
-- TOC entry 5061 (class 2606 OID 33156)
-- Name: son_asignadas son_asignadas_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.son_asignadas
    ADD CONSTRAINT son_asignadas_pkey PRIMARY KEY (id_postulacion, id_usuario);


--
-- TOC entry 5063 (class 2606 OID 33158)
-- Name: tipo_documentos tipo_documentos_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.tipo_documentos
    ADD CONSTRAINT tipo_documentos_pkey PRIMARY KEY (id_tipodocumento);


--
-- TOC entry 5065 (class 2606 OID 33160)
-- Name: tut_utec tut_utec_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.tut_utec
    ADD CONSTRAINT tut_utec_pkey PRIMARY KEY (id_tutor);


--
-- TOC entry 5035 (class 2606 OID 33341)
-- Name: instituciones uq_instituciones_nom_institucion; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.instituciones
    ADD CONSTRAINT uq_instituciones_nom_institucion UNIQUE (nom_institucion);


--
-- TOC entry 5069 (class 2606 OID 33162)
-- Name: usuarios uq_usuarios_correo; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.usuarios
    ADD CONSTRAINT uq_usuarios_correo UNIQUE (correo);


--
-- TOC entry 5067 (class 2606 OID 33164)
-- Name: usua_celulares usua_celulares_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.usua_celulares
    ADD CONSTRAINT usua_celulares_pkey PRIMARY KEY (celular, id_usuario);


--
-- TOC entry 5071 (class 2606 OID 33166)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 5073 (class 2606 OID 33168)
-- Name: valoraciones valoraciones_pkey; Type: CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.valoraciones
    ADD CONSTRAINT valoraciones_pkey PRIMARY KEY (id_criterio, id_evaluacion);


--
-- TOC entry 5077 (class 2606 OID 33169)
-- Name: carreras carreras_id_institucion_fkey; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.carreras
    ADD CONSTRAINT carreras_id_institucion_fkey FOREIGN KEY (id_institucion) REFERENCES proyecto.instituciones(id_institucion);


--
-- TOC entry 5078 (class 2606 OID 33174)
-- Name: convocatorias fk_convocatoria_rubrica; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.convocatorias
    ADD CONSTRAINT fk_convocatoria_rubrica FOREIGN KEY (id_rubrica) REFERENCES proyecto.rubricas(id_rubrica);


--
-- TOC entry 5079 (class 2606 OID 33179)
-- Name: criterios fk_criterios_seccion; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.criterios
    ADD CONSTRAINT fk_criterios_seccion FOREIGN KEY (id_secrubrica) REFERENCES proyecto.secciones_rubricas(id_secrubrica);


--
-- TOC entry 5094 (class 2606 OID 33184)
-- Name: postulaciones fk_depto_academico; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.postulaciones
    ADD CONSTRAINT fk_depto_academico FOREIGN KEY (id_departamento) REFERENCES proyecto.depa_academicos(id_departamento);


--
-- TOC entry 5080 (class 2606 OID 33189)
-- Name: docu_adjuntos fk_docu_adjuntos_postulacion; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.docu_adjuntos
    ADD CONSTRAINT fk_docu_adjuntos_postulacion FOREIGN KEY (id_postulacion) REFERENCES proyecto.postulaciones(id_postulacion);


--
-- TOC entry 5081 (class 2606 OID 33325)
-- Name: docu_adjuntos fk_docu_adjuntos_tipo_documento; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.docu_adjuntos
    ADD CONSTRAINT fk_docu_adjuntos_tipo_documento FOREIGN KEY (id_tipodocumento) REFERENCES proyecto.tipo_documentos(id_tipodocumento);


--
-- TOC entry 5093 (class 2606 OID 33194)
-- Name: post_celulares fk_documento_postulantes; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.post_celulares
    ADD CONSTRAINT fk_documento_postulantes FOREIGN KEY (id_postulante) REFERENCES proyecto.postulantes(id_postulante);


--
-- TOC entry 5083 (class 2606 OID 33199)
-- Name: evaluaciones fk_evaluaciones_postulacion; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.evaluaciones
    ADD CONSTRAINT fk_evaluaciones_postulacion FOREIGN KEY (id_postulacion) REFERENCES proyecto.postulaciones(id_postulacion);


--
-- TOC entry 5084 (class 2606 OID 33204)
-- Name: evaluaciones fk_evaluaciones_usuario; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.evaluaciones
    ADD CONSTRAINT fk_evaluaciones_usuario FOREIGN KEY (id_usuario) REFERENCES proyecto.usuarios(id_usuario);


--
-- TOC entry 5085 (class 2606 OID 33342)
-- Name: formaciones fk_formaciones_postulantes; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.formaciones
    ADD CONSTRAINT fk_formaciones_postulantes FOREIGN KEY (id_postulante) REFERENCES proyecto.postulantes(id_postulante);


--
-- TOC entry 5075 (class 2606 OID 33209)
-- Name: carr_form fk_id_carrera; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.carr_form
    ADD CONSTRAINT fk_id_carrera FOREIGN KEY (id_carrera) REFERENCES proyecto.carreras(id_carrera);


--
-- TOC entry 5076 (class 2606 OID 33219)
-- Name: carr_form fk_id_formacion; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.carr_form
    ADD CONSTRAINT fk_id_formacion FOREIGN KEY (id_formacion) REFERENCES proyecto.formaciones(id_formacion);


--
-- TOC entry 5090 (class 2606 OID 33224)
-- Name: posg_form fk_id_formacion; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.posg_form
    ADD CONSTRAINT fk_id_formacion FOREIGN KEY (id_formacion) REFERENCES proyecto.formaciones(id_formacion);


--
-- TOC entry 5091 (class 2606 OID 33229)
-- Name: posg_form fk_id_posgrado; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.posg_form
    ADD CONSTRAINT fk_id_posgrado FOREIGN KEY (id_posgrado) REFERENCES proyecto.posgrados(id_posgrado);


--
-- TOC entry 5086 (class 2606 OID 33234)
-- Name: incluyen fk_id_postulacion; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.incluyen
    ADD CONSTRAINT fk_id_postulacion FOREIGN KEY (id_postulacion) REFERENCES proyecto.postulaciones(id_postulacion);


--
-- TOC entry 5101 (class 2606 OID 33239)
-- Name: son_asignadas fk_id_postulacion; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.son_asignadas
    ADD CONSTRAINT fk_id_postulacion FOREIGN KEY (id_postulacion) REFERENCES proyecto.postulaciones(id_postulacion);


--
-- TOC entry 5087 (class 2606 OID 33244)
-- Name: incluyen fk_id_tutor; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.incluyen
    ADD CONSTRAINT fk_id_tutor FOREIGN KEY (id_tutor) REFERENCES proyecto.tut_utec(id_tutor);


--
-- TOC entry 5074 (class 2606 OID 33249)
-- Name: administradores fk_id_usuario; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.administradores
    ADD CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES proyecto.usuarios(id_usuario);


--
-- TOC entry 5082 (class 2606 OID 33254)
-- Name: eva_externos fk_id_usuario; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.eva_externos
    ADD CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES proyecto.usuarios(id_usuario);


--
-- TOC entry 5102 (class 2606 OID 33259)
-- Name: son_asignadas fk_id_usuario; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.son_asignadas
    ADD CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES proyecto.usuarios(id_usuario);


--
-- TOC entry 5103 (class 2606 OID 33264)
-- Name: usua_celulares fk_id_usuario; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.usua_celulares
    ADD CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES proyecto.usuarios(id_usuario);


--
-- TOC entry 5088 (class 2606 OID 33269)
-- Name: lista_prelacion fk_lista_ces; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.lista_prelacion
    ADD CONSTRAINT fk_lista_ces FOREIGN KEY (id_ces) REFERENCES proyecto.ces(id_ces);


--
-- TOC entry 5089 (class 2606 OID 33274)
-- Name: lista_prelacion fk_lista_convocatoria; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.lista_prelacion
    ADD CONSTRAINT fk_lista_convocatoria FOREIGN KEY (id_convocatoria) REFERENCES proyecto.convocatorias(id_convocatoria);


--
-- TOC entry 5095 (class 2606 OID 41062)
-- Name: postulaciones fk_postulacion_convocatoria; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.postulaciones
    ADD CONSTRAINT fk_postulacion_convocatoria FOREIGN KEY (id_convocatoria) REFERENCES proyecto.convocatorias(id_convocatoria);


--
-- TOC entry 5096 (class 2606 OID 33279)
-- Name: postulaciones fk_postulaciones_postulante; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.postulaciones
    ADD CONSTRAINT fk_postulaciones_postulante FOREIGN KEY (id_postulante) REFERENCES proyecto.postulantes(id_postulante);


--
-- TOC entry 5098 (class 2606 OID 33284)
-- Name: requieren fk_requieren_convocatoria; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.requieren
    ADD CONSTRAINT fk_requieren_convocatoria FOREIGN KEY (id_convocatoria) REFERENCES proyecto.convocatorias(id_convocatoria);


--
-- TOC entry 5099 (class 2606 OID 33289)
-- Name: requieren fk_requieren_tipo_documento; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.requieren
    ADD CONSTRAINT fk_requieren_tipo_documento FOREIGN KEY (id_tipodocumento) REFERENCES proyecto.tipo_documentos(id_tipodocumento);


--
-- TOC entry 5100 (class 2606 OID 33294)
-- Name: secciones_rubricas fk_seccion_rubrica; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.secciones_rubricas
    ADD CONSTRAINT fk_seccion_rubrica FOREIGN KEY (id_rubrica) REFERENCES proyecto.rubricas(id_rubrica);


--
-- TOC entry 5097 (class 2606 OID 33299)
-- Name: postulaciones fk_sedes; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.postulaciones
    ADD CONSTRAINT fk_sedes FOREIGN KEY (id_sede) REFERENCES proyecto.sedes(id_sede);


--
-- TOC entry 5104 (class 2606 OID 33304)
-- Name: valoraciones fk_valoraciones_criterio; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.valoraciones
    ADD CONSTRAINT fk_valoraciones_criterio FOREIGN KEY (id_criterio) REFERENCES proyecto.criterios(id_criterio);


--
-- TOC entry 5105 (class 2606 OID 33309)
-- Name: valoraciones fk_valoraciones_evaluacion; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.valoraciones
    ADD CONSTRAINT fk_valoraciones_evaluacion FOREIGN KEY (id_evaluacion, id_usuario, id_postulacion) REFERENCES proyecto.evaluaciones(id_evaluacion, id_usuario, id_postulacion);


--
-- TOC entry 5092 (class 2606 OID 33314)
-- Name: posgrados posgrados_id_institucion_fkey; Type: FK CONSTRAINT; Schema: proyecto; Owner: postgres
--

ALTER TABLE ONLY proyecto.posgrados
    ADD CONSTRAINT posgrados_id_institucion_fkey FOREIGN KEY (id_institucion) REFERENCES proyecto.instituciones(id_institucion);


-- Completed on 2026-11-06 22:52:08

--
-- PostgreSQL database dump complete
--

--\unrestrict i0hNBdnlxhQa2OjdFJR5MFUtvHuv03kvVbyZrve3HzvOfTgYwdgp0KAmYAqdDnq


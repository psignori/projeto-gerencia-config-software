-- Database: ProjQuarta

-- DROP DATABASE "ProjQuarta";

CREATE DATABASE "ProjQuarta"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Criando Tabelas

-- Table: public.pessoa

-- DROP TABLE public.pessoa;

CREATE TABLE public.pessoa ( id_pessoa SERIAL, nome character varying(100) COLLATE pg_catalog."default" NOT NULL, data_nascimento date NOT NULL, email character varying(200) COLLATE pg_catalog."default" NOT NULL, senha character varying(100) COLLATE pg_catalog."default" NOT NULL, CONSTRAINT pk_id_pessoa PRIMARY KEY (id_pessoa), CONSTRAINT unique_email UNIQUE (email));

TABLESPACE pg_default;

ALTER TABLE public.pessoa
    OWNER to postgres;


-- Table: public.despesas

-- DROP TABLE public.despesas;

CREATE TABLE public.despesas ( id_despesa SERIAL, nome character varying(50) COLLATE pg_catalog."default" NOT NULL, valor numeric(10,2) NOT NULL, descricao character varying(800) COLLATE pg_catalog."default", id_pessoa integer NOT NULL, CONSTRAINT pk_id_despesa PRIMARY KEY (id_despesa), CONSTRAINT fk_id_pessoa FOREIGN KEY (id_pessoa) REFERENCES public.pessoa (id_pessoa) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);

TABLESPACE pg_default;

ALTER TABLE public.despesas
    OWNER to postgres;
	
-- Table: public.receitas

-- DROP TABLE public.receitas;

CREATE TABLE public.receitas( id_receita SERIAL, nome character varying(50) COLLATE pg_catalog."default" NOT NULL, valor numeric(10,2) NOT NULL, descricao character varying(800) COLLATE pg_catalog."default", id_pessoa integer NOT NULL, CONSTRAINT pk_id_receita PRIMARY KEY (id_receita), CONSTRAINT fk_id_pessoa FOREIGN KEY (id_pessoa) REFERENCES public.pessoa (id_pessoa) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);

TABLESPACE pg_default;

ALTER TABLE public.receitas
    OWNER to postgres;

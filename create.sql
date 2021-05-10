SET MODE PostgreSQL;

CREATE DATABASE news_portal;
\c news_portal

CREATE TABLE departments (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR,
                             decription VARCHAR,
                             num_employees int
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR,
                       dep_id int,
                       position VARCHAR

);

CREATE TABLE news (
                      id SERIAL PRIMARY KEY,
                      dep_id int,
                      title VARCHAR,
                      body VARCHAR,
                      writtenby VARCHAR,
                      type VARCHAR

);

CREATE TABLE departments_news(
                                 id SERIAL PRIMARY KEY,
                                 dep_id int,
                                 news_id int

);


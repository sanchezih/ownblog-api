-- Inicializacion de la tabla roles
INSERT INTO public.roles(name) VALUES('ROLE_USER');
--INSERT INTO public.roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO public.roles(name) VALUES('ROLE_ADMIN');

-- Inicializacion de la tabla categoria
INSERT INTO public.categoria (descripcion, nombre) VALUES('Historia', 'historia');
INSERT INTO public.categoria (descripcion, nombre) VALUES('Geografia', 'geografia');
INSERT INTO public.categoria (descripcion, nombre) VALUES('Entretenimiento', 'Entretenimiento');
INSERT INTO public.categoria (descripcion, nombre) VALUES('Arte', 'arte');
INSERT INTO public.categoria (descripcion, nombre) VALUES('Deportes', 'deportes');
INSERT INTO public.categoria (descripcion, nombre) VALUES('Ciencia', 'ciencia');

-- Inicializacion de la tabla pregunta
INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿Quién fue el primer presidente de la democracia española tras el franquismo?', 1);
INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿La invasión de qué fortaleza por parte de los revolucionarios es considerada como el punto de inicio de la Revolución Francesa?', 1);

INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿Cuál es el río más caudaloso del mundo?', 2);
INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿Cuál es el monte más alto del mundo?', 2);

INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿A quién interpretaba John Travolta en Grease?', 3);
INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿Qué conocido cómico imitó a Hitler en la película El Gran Dictador?', 3);

INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿Quién escribió la Ilíada y la Odisea?', 4);
INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿Quién pintó el Guernica?', 4);

INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿Cuándo se celebró la primera Copa Mundial de Fútbol?', 5);
INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿Quién es considerado el mejor jugador de baloncesto de todos los tiempos?', 5);

INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿Cuál es la velocidad de la luz?', 6);
INSERT INTO public.pregunta (enunciado, categoria_id) VALUES('¿Qué gas nos protege de la radiación solar, concretamente de la radiación ultravioleta, formando una capa en la atmósfera?', 6);

-- Inicializacion de la tabla respuesta

-- Respuestas correctas
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, 'Adolfo Suárez', 1);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, 'La toma de la Bastilla', 2);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, 'Amazonas', 3);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, 'Everest', 4);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, 'Danny Zuko', 5);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, 'Charles Chaplin', 6);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, 'Homero', 7);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, 'Pablo Picasso', 8);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, '1930', 9);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, 'Michael Jordan', 10);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, '300.000.000 km/s', 11);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(true, 'El ozono', 12);

-- Respuestas incorrectas
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 1);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 1);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 1);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 2);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 2);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 2);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 3);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 3);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 3);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 4);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 4);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 4);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 5);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 5);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 5);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 6);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 6);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 6);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 7);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 7);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 7);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 8);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 8);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 8);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 9);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 9);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 9);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 10);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 10);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 10);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 11);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 11);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 11);

INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 1', 12);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 2', 12);
INSERT INTO public.respuesta (es_correcta, texto, pregunta_id) VALUES(false, 'Respuesa incorrecta 3', 12);
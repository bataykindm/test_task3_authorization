<h1>
  <p align="center">Authorization app</p>
</h1>

<b><p align="center">Описание</p></b>

<p>
Тестовое RESTful приложение, позволяющее добавлять сообщения в БД и получать последние 10 (согласно заданию). Работать с БД сообщений может только авторизованный пользователь. Аутентификация проходит путем сравнения хэш-кода пароля в БД и хэш-кода, который генерируется сервисом при передаче пароля. 

Стек: Java8, SpringBoot, SpringSecurity, Maven, PostgreSQL, Docker, Git.
</p>

<b><p align="center">Перед началом</p></b> 
<p>

Для опробования работы приложения до старта, согласно используемой БД, необходимо изменить в файле application.properties параметры БД, такие как:

<em>

spring.datasource.url

spring.datasource.username

spring.datasource.password

</em>

Для использования отличной от PostgreSQL БД необходимо также пересмотреть на соответствующие БД параметры:

<em>

spring.datasource.driver-class-name

spring.jpa.database

spring.jpa.database-platform

</em>

По умолчанию используемый порта 8175:

<em>

server.port=8175

</em>

Также данный файл содержит параметры для работы с JWT:

<em>jwt.token.secret=inside</em> – для подписи

<em>jwt.token.expired=120000</em> – время «существования» токена

</p>

<b><p align="center">Старт</p></b> 
<p>
При использовании исходного кода*

Приложение можно завернуть в docker image, для этого в проекте содержится файл Dockerfile описывающий этот процесс. 
Необходимые команды для создания образа (вызываем через командную строку в корне проекта):

<em>mvn clean install</em> – для сборки проекта

<em>docker build -t inside</em> – создание docker image

<em>docker run -p 8175:8175 --name inside_container -d inside</em> – запуск в работу контейнера приложения с назначенным при старте именем “inside_container” на порту 8175

<em>docker –kill inside_container</em> – для остановки работы приложения

*Также возможно использование ранее созданного docker образа по ссылке https://hub.docker.com/r/bataykindm/inside
</em>

<h2>
  <p align="center">Таблица запросов</p>
</h2>

<table align="center">
	<thead>
		<tr>
			<th>Method</th>
			<th>Path</th>
			<th>Option</th>
      			<th>Curl-Request</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><strong>POST</strong></td>
			<td>/auth/login/</td>
			<td>Аутентификация предоставленного аккаунта используя пароль</td>
      			<td>curl -X POST "http://localhost:8175/auth/login" -H "Accept:application/json" -H "Content-Type:application/json" -d "{\"username\":\"-----\",\"password\":\"-----\"}"</td>
		</tr>
		<tr>
			<td><strong>POST</strong></td>
      			<td>/messages</td>
			<td>Сохранение предоставленной сущности-сообщения. Возвращает сохраненную сущность-сообщение</td>
          		<td>curl -X POST "http://localhost:8175/messages" -H "Accept:application/json" -H "Content-Type:application/json" –H "Authorization:Bearer_token" -d "{\"username\":\"-----\",\"text\":\"-----\"}"</td>
        	</tr>
        	<tr>
			<td></td>
			<td></td>
          		<td>При передачи в поле “text” значение “history 10” метод возвращает последние 10 сообщений из БД</td>
          		<td>curl -X POST "http://localhost:8175/messages" -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization:Bearer_token " -d "{\"username\":\"-----\",\"text\":\"history 10\"}"</td>
        	</tr>
	</tbody>
</table>

<b><p align="center">Опробование</p></b> 
<p>

Для проверки работы приложения в проекте содержится файл import.sql для Hibernate, который заполнит начальными для проверки приложения данными им же созданную таблицу:

<p align="center">INSERT INTO accounts (id, username, password) VALUES (321, 'inside', '$2a$04$C5dxv2YjVhh7udwRZ4Pre.GA9Jsqu2wt2pgxH0bDxBkSYtqK.56cW');</p>

Поле “password” хранится в БД в виде хэш-кода, который генерируется при регистрации аккаунта. Для генерации данного хэш-кода использовался
https://www.devglan.com/online-tools/bcrypt-hash-generator для пароля “inside”.

Согласно таблицы запросов воспользуемся командной строкой и передадим приложению curl-запросы. Для начала произведем аутентификацию аккаунта с полями username=inside и password=inside.

curl -X POST "http://localhost:8175/auth/login" -H "Accept:application/json" -H "Content-Type:application/json" -d "{\"username\":\"inside\",\"password\":\"inside\"}"
Ответом на данный запрос будет с полем <b>token</b>. 

Данное поле необходимо скопировать и вставить в следующий curl-запрос в <b>token</b>.

curl -X POST "http://localhost:8175/messages" -H "Accept:application/json" -H "Content-Type:application/json" –H "Authorization:Bearer_<b>token</b>" -d "{\"username\":\"inside\",\"text\":\"message1\"}"

Данный запрос сохранит сущность Message в БД и вернет сохраненную сущность с полями, содержащими инфекцию о том, кем отправлено сообщение: username и содержимое сообщения: text.

Для того, чтобы вернуть последние 10 добавленных в БД сообщений, необходимо направить аналогичный запрос, однако переданное поле “text” должно содержать “history 10”.

curl -X POST "http://localhost:8175/messages" -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization:Bearer_<b>token</b>" -d "{\"username\":\"inside\",\"text\":\"history 10\"}"
</p>

<b><p align="center">Ссылки</p></b> 
<p>
https://github.com/bataykindm/test_task3_authorization - исходный код приложения

https://hub.docker.com/r/bataykindm/inside - образ приложения
</p>





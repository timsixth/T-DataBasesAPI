Simple (spigot plugin) api to connecting to database.<br/>
More information on wiki: https://github.com/timsixth/T-DataBasesAPI/wiki<br/>
You don't have to write some code to connect to mysql or sqlite.
Only you have to type in config hostname,password .etc.
Immediately you can excute query, without writing code to connect to database.
Api works on spigot.

JavaDocs: https://timsixth.pl/javadocs/databases_api/1.7.1/

How to execute query to database? It is very simple.
```java
ResultSet resultSet = main.getDataBaseApi().getCurrentSqlDataBase().query("SELECT * FROM test").executeQuery();
while(resultSet.next()){
    System.out.println(resultSet.getInt("id"))
}
```
config.yml
```yaml
hostname: 'localhost'
username: 'root'
password: ''
database: 'servertestowy'
port: 3306
type: MYSQL
```

Maven
```xml
<repositories>
  <repository>
     <id>jitpack.io</id>
     <url>https://jitpack.io</url>
   </repository>
</repositories>
  
<dependency>
  <groupId>com.github.timsixth</groupId>
  <artifactId>T-DataBasesAPI</artifactId>
  <version>VERSION</version>
</dependency>
```
Gradle
```gradle
allprojects {
   repositories {
	maven { url 'https://jitpack.io' }
	}
}
  dependencies {
	implementation 'com.github.timsixth:T-DataBasesAPI:VERSION'
}
```

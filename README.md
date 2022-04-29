Simple (spigot/bungee plugin) api to connecting to database.

You don't have to write some code to connect to mysql or sqlite.
Only you have to type in config hostname,password .etc.
Immediately you can excute query, without writing code to connect to database.
Api works on spigot and bungeecord.

How execute query to database. It is very simple.
```java
private final DatabasesAPISpigot databasesAPISpigot = DatabasesAPISpigot.getInstance(); //Get the instance of Api for spigot

 try (ResultSet resultSet = databasesAPISpigot.getCurrentSQLDataBase().query("SELECT * FROM test").executeQuery()) { //executing query
       while (resultSet.next()) {
          System.out.println(resultSet.getString("ID"));
       }
   } catch (SQLException e) {
       e.printStackTrace();
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
  <version>v1.0</version>
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
	implementation 'com.github.timsixth:T-DataBasesAPI:v1.0'
}
```

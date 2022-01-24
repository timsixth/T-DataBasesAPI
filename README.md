Simple api to connecting to database.

You don't have to write some code to connect to mysql or sqlite.
Only you have to type in config hostname,password .etc.
Immediately you can excute query, without writing code to connect to database.

How execute query to database. It is very simple.
```java


```
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
```xml
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  dependencies {
	        implementation 'com.github.timsixth:T-DataBasesAPI:v1.0'
	}
```

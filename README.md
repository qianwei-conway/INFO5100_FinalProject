### How to test qianwei's final project

<br>
<br>

1. Downloads

Please download "Downloads.zip" and unzip it.

<br>

2. Open project

Open my whole project.

<br>

3. Import .jar files

Go to **File - Project Structure - Project Settings - Modules - Dependencies**.

Click **"+"**, i.e. Add

Then import **jdatepicker-1.3.4.jar** and **mysql-connector-java-8.0.11.jar**.

Click **"Apply"** and then **"OK"**

<br>

4. Download MySQL

If you already have MySQL on your computer, then skip this part.

Go to https://downloads.mysql.com/archives/community/ and choose your operating system and version.

Version 8.0.11 is better, since it matches my MySQL connector jar file.

But other version is okay in most occassions.

<br>

5. Create database and import data using terminal

**Open your terminal** and follow the steps.

`mysql -uroot -p` and enter your MySQL password

`create DATABASE qianwei` or if you do not want to create a new database, you can skip this line.

`use qianwei` or any database that you already have

`source PATH`

PATH is the absolute path of the **"dump.sql"** in Downloads directory.

Then exit the terminal.

<br>

6. Create connection between your IDE and database

Go to your Intellij page, then in your side bar, there will be "Database" option.

Open and click **"+" - Data Source - MySQL**.

<br>

User: your MySQL's username, in most cases is **root**.

Password: your MySQL's password.

Database: **qianwei**, or other name you use in the 5th step.

Click **"Apply"** and then **"OK"**

<br>

7. Then go to run Main.java

At the beginning, you need to input something related to your database in the console.

But this is **local operation** so you don't have to be afraid of leaking privacy!

<br> 

I have tried my best to make the steps detailed. But as known, configuring environment is the hardest part of doing a project......

So if you find any step which doesn't work well on your computer, please contact me and I wanna find a solution!

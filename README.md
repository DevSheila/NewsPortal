# NEWS PORTAL
### Author- Sheila Sharon Wambui Karani.

## Description
This is a REST API for querying and retrieving scoped news and information of an organisation.
## Table of contents

* [Demo](#demo)

* [Technologies](#technologies)

* [Known Bugs](#knownbugs)

* [Setup](#setup)

* [Routes](#routes)

* [Contact](#contact)

* [Licence](#Licence)


## Demo
Link to live site:

## Technologies

1. Java
1. Junit
1. Gradle
1. Spark
1. Handlebars
1. CSS
1. postgres





## Known Bugs
There are curently no known bugs.
## Setup
### Prerequisites
You will need to have
1. JRE & JDK
1. SDK
1. Gradle
1. Junit
1. Spark
1. Intellij


### Cloning
* you can clone it directly to your folder:

```
$ https://github.com/DevSheila/NewsPortal.git

```
* Alternatively,you can download  manually and pass it to your root directory.

* Extract Files

    * Open the file with inteliiJ
    * Navigate to your terminal
    * cd IdeaProjects/NewsPortal

### Database Setup
To create our development and test databases, you can run from the command line:
```
$ psql < create.sql
```
If you get any errors about the databases already existing, you may need to first clear them by running drop.sql file through psql:
```
$ psql < drop.sql
```
### Run Locally
Run command:

```
$ gradle run

```
## Routes

### users
1. /users
1. /users/new
1. /users/:id

###departments
1. /departments
1. /departments/:id
###departmental news
1. /departments/:id/news
1. /departments/:id/news/:newsId
###general news
1. /generalNews
1. /generalNews/new
1. /generalNews/:id

## Contact
In case of any question or contributions, contact me at sheilasharon10@gmail.com


## Licence
MIT License

Copyright (c) 2021 Sheila Sharon

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

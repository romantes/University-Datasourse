Steps which needed to execute programm:
1.Use University.sql to create db with tables and data in there
2.Configure properties for jdbc connection in db.properties file
3.Use Main class to execute programm methods

note: log4j configured to input trace layer to log.txt,
any changes can be made in log4j.properties according to your
preferences.

note: LectureDAO.class contains no UPDATE method for lecture object:
 

note: id field sequences in professors and students tables is stars from 1 and 2 
thats rule help indentify what kind of schedule shoud be extracted to console output.

note: group can only be deleted in case when there is no referenced students to it

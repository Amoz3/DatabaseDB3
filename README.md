DatabaseDB3 - class for interacting with psql database in DB3 scripts

database table should use username as the primary key and should be named "main" 


METHODS:

---------

private getConnection() 

returns a connection to be used in other public methods

-----

public sendLog(String task)

returns void, inserts/updates username, gp, script, task & age in database, will only work on db structured with those columns

-----

public updateCol(String column, String data) 

returns true if successful

updates a psql table column column to data

------

public init()

returns true if successful (havent tested that) 

because username is primary key, you cant use updateCol() until this or sendLog() is used, for tables with different columns than that in sendLog() you can use this to get your entry





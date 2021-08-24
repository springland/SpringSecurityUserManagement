
If the http.csrf().disable() is called to turn off csrf
then SpringSecurity is disabled. The UserDetailsService is not called when http request comes in

In order to access H2 console to check data the below is required

http.csrf().disable();
http.headers().frameOptions().disable();


H2 database changes table and schema database to upper case by default
This can be changed to add ;DATABASE_TO_UPPER=false into jdbc url

Another issue is if there are anything wrong with SQL query for example the table does not exist the exception was discard by security framework. It is handled as login failure

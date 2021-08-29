
***H2 database issue***

If the http.csrf().disable() is called to turn off csrf
then SpringSecurity is disabled. The UserDetailsService is not called when http request comes in

In order to access H2 console to check data the below is required

http.csrf().disable();
http.headers().frameOptions().disable();


H2 database changes table and schema database to upper case by default
This can be changed to add ;DATABASE_TO_UPPER=false into jdbc url

Another issue is if there are anything wrong with SQL query for example the table does not exist the exception was discard by security framework. It is handled as login failure

***Password Encoder***
Pbkdf2PasswordEncoder 

BCryptPasswordEncoder 
BCryptPasswordEncoder with strength 16 and SecureRandom is a lot slower than the one with default constructor

check out server.ldif mike and mike_s_s

SCryptPasswordEncoder
check out server.ldif peter


Role and permission
Check out server.ldif , there are two groups defined. user and admin
Spring security changes the group name to be upper case
Then the antMatchers are defined with upper case role
http.authorizeRequests()
.antMatchers("/hello").hasRole("USER")
.antMatchers("/greeting").hasRole("ADMIN")

***Antmatcher vs mvcmatcher***
https://stackoverflow.com/questions/50536292/difference-between-antmatcher-and-mvcmatcher
# WildFly DataSource Listeners

This project demonstrates how to attach a listener to WildFly's datasources.

That can be used to get notifications about when a JDBC connection is taken out of the connection pool and given back again.

NOTE: The recommended way is to specify the listener in the datasource definition XML stanza in standalone.xml (/domain.xml, *-ds.xml). This example uses a nasty reflection trick to get it done automatically when the application is started. Just for fun :)


# Shows @Singleton lock behavior

A @Singleton has implicit @Lock(WRITE) locks on it - unless otherwise configured by the bean developer.

But did you know that a @Lock(READ) locked @Singleton still may expose @Lock(WRITE) locked methods if it inherits those methods from it's parent (and it is part of the enterprise bean view) ?

That may give you some interesting errors when combined with re-entrant method calls to the so-called @Lock(READ) EJB.... check it out and observe WildFly explode.


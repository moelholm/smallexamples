# hello-arquillian-wildfly-multideployment

This project shows how to run an arquillian test towards an existing EAR file in WildFly 8.2.

So the motivation should be: 
- You want to introduce Arquillian integration tests into a legacy project 
- But this legacy project has the worlds most complex relationships that with almost all certainty results in crazy fat Arquillian deployments - effectively making the integration tests run in O(n^n^n^n^n^n) 

Fear not. 

Just deploy your fat EAR file. Then run the Arquillian tests next to it. In order to do so you must open up the classpath so that the test archives (which Arquillian deploys for you) can see the EAR submodules. Once that is done: just lookup the EJBs local views and start interacting with the EAR file.

Obviously this is a suboptimal solution in terms of TDD productivity: since after all you need to have the EAR file deployed. But perhaps JRebel can help you there. 

( Otherwise redesign your legacy application so that small arquillian deployments are possible ... :) ) 

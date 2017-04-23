

/**
 * MelbourneWeather2Test.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package MelbourneWeather2;

    /*
     *  MelbourneWeather2Test Junit test case
    */

    public class MelbourneWeather2Test extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testgetRainfall() throws java.lang.Exception{

        MelbourneWeather2.MelbourneWeather2Stub stub =
                    new MelbourneWeather2.MelbourneWeather2Stub();//the default implementation should point to the right endpoint

           MelbourneWeather2.MelbourneWeather2Stub.GetRainfall getRainfall13=
                                                        (MelbourneWeather2.MelbourneWeather2Stub.GetRainfall)getTestObject(MelbourneWeather2.MelbourneWeather2Stub.GetRainfall.class);
                    // TODO : Fill in the getRainfall13 here
                
                        assertNotNull(stub.getRainfall(
                        getRainfall13));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartgetRainfall() throws java.lang.Exception{
            MelbourneWeather2.MelbourneWeather2Stub stub = new MelbourneWeather2.MelbourneWeather2Stub();
             MelbourneWeather2.MelbourneWeather2Stub.GetRainfall getRainfall13=
                                                        (MelbourneWeather2.MelbourneWeather2Stub.GetRainfall)getTestObject(MelbourneWeather2.MelbourneWeather2Stub.GetRainfall.class);
                    // TODO : Fill in the getRainfall13 here
                

                stub.startgetRainfall(
                         getRainfall13,
                    new tempCallbackN1000C()
                );
              


        }

        private class tempCallbackN1000C  extends MelbourneWeather2.MelbourneWeather2CallbackHandler{
            public tempCallbackN1000C(){ super(null);}

            public void receiveResultgetRainfall(
                         MelbourneWeather2.MelbourneWeather2Stub.GetRainfallResponse result
                            ) {
                
            }

            public void receiveErrorgetRainfall(java.lang.Exception e) {
                fail();
            }

        }
      
        /**
         * Auto generated test method
         */
        public  void testgetLocations() throws java.lang.Exception{

        MelbourneWeather2.MelbourneWeather2Stub stub =
                    new MelbourneWeather2.MelbourneWeather2Stub();//the default implementation should point to the right endpoint

           
                        assertNotNull(stub.getLocations(
                        ));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartgetLocations() throws java.lang.Exception{
            MelbourneWeather2.MelbourneWeather2Stub stub = new MelbourneWeather2.MelbourneWeather2Stub();
             

                stub.startgetLocations(
                         
                    new tempCallbackN10049()
                );
              


        }

        private class tempCallbackN10049  extends MelbourneWeather2.MelbourneWeather2CallbackHandler{
            public tempCallbackN10049(){ super(null);}

            public void receiveResultgetLocations(
                         MelbourneWeather2.MelbourneWeather2Stub.GetLocationsResponse result
                            ) {
                
            }

            public void receiveErrorgetLocations(java.lang.Exception e) {
                fail();
            }

        }
      
        /**
         * Auto generated test method
         */
        public  void testgetTemperature() throws java.lang.Exception{

        MelbourneWeather2.MelbourneWeather2Stub stub =
                    new MelbourneWeather2.MelbourneWeather2Stub();//the default implementation should point to the right endpoint

           MelbourneWeather2.MelbourneWeather2Stub.GetTemperature getTemperature17=
                                                        (MelbourneWeather2.MelbourneWeather2Stub.GetTemperature)getTestObject(MelbourneWeather2.MelbourneWeather2Stub.GetTemperature.class);
                    // TODO : Fill in the getTemperature17 here
                
                        assertNotNull(stub.getTemperature(
                        getTemperature17));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartgetTemperature() throws java.lang.Exception{
            MelbourneWeather2.MelbourneWeather2Stub stub = new MelbourneWeather2.MelbourneWeather2Stub();
             MelbourneWeather2.MelbourneWeather2Stub.GetTemperature getTemperature17=
                                                        (MelbourneWeather2.MelbourneWeather2Stub.GetTemperature)getTestObject(MelbourneWeather2.MelbourneWeather2Stub.GetTemperature.class);
                    // TODO : Fill in the getTemperature17 here
                

                stub.startgetTemperature(
                         getTemperature17,
                    new tempCallbackN10083()
                );
              


        }

        private class tempCallbackN10083  extends MelbourneWeather2.MelbourneWeather2CallbackHandler{
            public tempCallbackN10083(){ super(null);}

            public void receiveResultgetTemperature(
                         MelbourneWeather2.MelbourneWeather2Stub.GetTemperatureResponse result
                            ) {
                
            }

            public void receiveErrorgetTemperature(java.lang.Exception e) {
                fail();
            }

        }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    
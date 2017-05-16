

/**
 * MelbourneWeatherTimeLapseTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package MelbourneWeatherTimeLapse;

    /*
     *  MelbourneWeatherTimeLapseTest Junit test case
    */

    public class MelbourneWeatherTimeLapseTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testgetWeather() throws java.lang.Exception{

        MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub stub =
                    new MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub();//the default implementation should point to the right endpoint

           MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetWeather getWeather9=
                                                        (MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetWeather)getTestObject(MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetWeather.class);
                    // TODO : Fill in the getWeather9 here
                
                        assertNotNull(stub.getWeather(
                        getWeather9));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartgetWeather() throws java.lang.Exception{
            MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub stub = new MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub();
             MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetWeather getWeather9=
                                                        (MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetWeather)getTestObject(MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetWeather.class);
                    // TODO : Fill in the getWeather9 here
                

                stub.startgetWeather(
                         getWeather9,
                    new tempCallbackN1000C()
                );
              


        }

        private class tempCallbackN1000C  extends MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseCallbackHandler{
            public tempCallbackN1000C(){ super(null);}

            public void receiveResultgetWeather(
                         MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetWeatherResponse result
                            ) {
                
            }

            public void receiveErrorgetWeather(java.lang.Exception e) {
                fail();
            }

        }
      
        /**
         * Auto generated test method
         */
        public  void testgetLocations() throws java.lang.Exception{

        MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub stub =
                    new MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub();//the default implementation should point to the right endpoint

           
                        assertNotNull(stub.getLocations(
                        ));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartgetLocations() throws java.lang.Exception{
            MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub stub = new MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub();
             

                stub.startgetLocations(
                         
                    new tempCallbackN10049()
                );
              


        }

        private class tempCallbackN10049  extends MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseCallbackHandler{
            public tempCallbackN10049(){ super(null);}

            public void receiveResultgetLocations(
                         MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetLocationsResponse result
                            ) {
                
            }

            public void receiveErrorgetLocations(java.lang.Exception e) {
                fail();
            }

        }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    
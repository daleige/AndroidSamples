class TestMain{

    public class Anima{

        protected String getName(){
            return null;
        };
    }

    public class Person extends Anima{
        private String name="人类";

        @Override
        public String getName() {
            return super.getName();
        }
    }

}
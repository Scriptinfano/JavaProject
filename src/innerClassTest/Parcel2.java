package innerClassTest;

//使用接口实现内部类和向上转型
interface Destination {
    String ReadLabel();
}

interface Contents {
    int value();
}

public class Parcel2 {
    private class PContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }

        public Destination destination(String s) {
            return new PDestination(s);
        }

        public Contents contents() {
            return new PContents();
        }

        protected final class PDestination implements Destination {
            private String label;

            private PDestination(String whereTo) {
                label = whereTo;
            }

            @Override
            public String ReadLabel() {
                return label;
            }
        }

    }
}

class TestMain {
    private Boolean mark;

    public void showMark() {
        System.out.println(mark);
    }

    public boolean getMark() {
        return mark;
    }

}

class TestMain2 {
    public static void main(String[] args) {
        TestMain main = new TestMain();
        System.out.println(main.getMark());
    }
}
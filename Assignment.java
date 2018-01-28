// Arv og polymorfi, del 1

class Assignment {
    public final static double PREPARATION = 3.0;
    private final int assessorID;
    private final String course;

    public Assignment(int assessorID, String course) {
        this.assessorID = assessorID;
        this.course = course;

    }

    public int getAssessorID() { return assessorID; }
    public String getCourse() { return course; }
    // Dummy method
    public double calculateHours() { return 0.0; }

}

class OralExam extends Assignment {
    private final double usedTime;

    public OralExam(int assessorID, String course, double usedTime) {
        super(assessorID, course);
        this.usedTime = usedTime;
    }

    // Overskriver metode i superklasse
    @Override
    public double calculateHours() { return usedTime + PREPARATION; }
}

class Written extends Assignment {
    private final int numberOfPapers;

    public Written(int assessorID, String course, int numberOfPapers) {
        super(assessorID, course);
        this.numberOfPapers = numberOfPapers;
    }

    public int getNumberOfPapers() { return numberOfPapers; }

}

class WrittenExam extends Written {
    public static final double FAKTOR1 = 0.15;
    public static final double FAKTOR2 = 0.1;
    public static final int NUMBER = 10;
    private final double numberOfHours;


    public WrittenExam(int assessorID, String course, int numberOfPapers, double numberOfHours) {
        super(assessorID, course, numberOfPapers);
        this.numberOfHours = numberOfHours;
    }

    @Override
    public double calculateHours() {
        int numberOfPapers = getNumberOfPapers();
        double hoursSpent = numberOfPapers * numberOfHours * ((numberOfPapers <= NUMBER) ? FAKTOR1 : (10 * FAKTOR1 + FAKTOR2 * (numberOfPapers-10)) / numberOfPapers);
        return hoursSpent + PREPARATION;
    }
}

class ProjectAssignment extends Written {
    public static final double HOURS_PER_PROJECT = 8;

    public ProjectAssignment(int assessorID, String course, int numberOfPapers) {
        super(assessorID, course, numberOfPapers);
    }

    public double calculateHours() {
        return getNumberOfPapers() * HOURS_PER_PROJECT;
    }

}

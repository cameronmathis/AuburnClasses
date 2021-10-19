/**
 * @author Cameron Mathis
 * @version 11 February 2017
 */
public class Grade {
   // instance variables
   private double exam1;
   private double exam2;
   private double finalExam;
   private double activityAvg;
   private double quizAvg;
   private double projectAvg;
   private String studentName;
   // constants
   /**  */
   public static final int EXAM_1 = 1, EXAM_2 = 2, FINAL = 3;
   private static final double EXAM_WEIGHT = 0.15, FINAL_WEIGHT = 0.30, 
      ACT_WEIGHT = 0.05, QUIZ_WEIGHT = 0.10, PROJ_WEIGHT = 0.25;
      
   // constructor  
   /**  
    *  @param studentNameIn to determine studentName.
    */
   public Grade(String studentNameIn) {
      studentName = studentNameIn;
   }
   
   // methods
   /**
    * @param activityAvgIn to determine activityAvg.
    * @param quizAvgIn to determine quizAvg.
    */
   public void setLabAverages(double activityAvgIn, double quizAvgIn) {
      activityAvg = activityAvgIn;
      quizAvg = quizAvgIn;
   }
   
   /**
    * @param projectAvgIn to determine projectAvg.
    */
   public void setProjectAvg(double projectAvgIn) {
      projectAvg = projectAvgIn;
   }
   
   /**
    * @param examType to determine the exam type.
    * @param examScoreIn to determine examScore.
    */
   public void setExamScore(int examType, double examScoreIn) {
      if (examType == EXAM_1) {
         exam1 = examScoreIn;
      }
      else if (examType == EXAM_2) {
         exam2 = examScoreIn;
      }
      else if (examType == FINAL) {
         finalExam = examScoreIn;
      }
   }
   
   /**
    * @return grade.
    */
   public double calculateGrade() {
      double grade = exam1 * EXAM_WEIGHT + exam2 * EXAM_WEIGHT
         + finalExam * FINAL_WEIGHT
         + activityAvg * ACT_WEIGHT
         + quizAvg * QUIZ_WEIGHT
         + projectAvg * PROJ_WEIGHT;
      return grade;
   }
   
   /**
    * @return "".
    */
   public String toString() {
      return "Name: " + studentName + "\n"
         + "Course Grade: " + calculateGrade();
   }
}

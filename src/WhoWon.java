public class WhoWon {

    private int compWin;
    private int userWin;
    private int lettersMissed;

    public int getCompWin(){
        return compWin;
    }
    public int setCompWin(int compWinAmount){
        compWin = compWinAmount;
        return compWinAmount;
    }
    public int getUserWin(){
        return userWin;
    }
    public int setUserWin(int userWinAmount){
        userWin = userWinAmount;
        return userWinAmount;
    }
    public int getLettersMissed(){
        return lettersMissed;
    }
    public int setLettersMissed(int lettersMissedAmount){
        lettersMissed = lettersMissedAmount;
        return lettersMissedAmount;
    }
}
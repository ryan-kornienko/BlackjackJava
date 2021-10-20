package blackjack;

public class Card {
    private char cardFace;
    private int value;
    public Card(char cardFace)throws Exception{
        this.cardFace = cardFace;
        switch(cardFace){
            case '1': value = 1;
                break;
            case '2': value = 2;
                break;
            case '3': value = 3;
                break;
            case '4': value = 4;
                break;
            case '5': value = 5;
                break;
            case '6': value = 6;
                break;
            case '7': value = 7;
                break;
            case '8': value = 8;
                break;
            case '9': value = 9;
                break;
            case '0': //0 represents card face 10
            case 'j': //jack, queen, and king face cards all have a value of 10
            case 'q':
            case 'k':
                value = 10;
                break;
            case 'a': value = 11; //aces are initialized with value of 11, will be handled later if it needs to change to 1
                break;
            default : throw new Exception("Card face character invalid"); //throw an exception if the user inputs an incorrect value
        }
    }

    public char getCardFace() {
        return cardFace;
    }

    public int getValue(){
        return value;
    }
}

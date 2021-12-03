package blackjack;

/**
 * Card class acts as a Card in a deck of cards that has a card face and value
 */
public class Card {
    private final char cardFace;
    private int value;

    /**
     * Card objects will act as a Card in a deck of cards that has a card face and value
     * @param cardFace acts at the card face for face values of 2-9, T, J, Q, K, A
     * @throws Exception will be thrown if the cardFace inputted is invalid
     */
    public Card(char cardFace) throws Exception {
        this.cardFace = cardFace;
        switch (cardFace) {
            case '2':
                value = 2;
                break;
            case '3':
                value = 3;
                break;
            case '4':
                value = 4;
                break;
            case '5':
                value = 5;
                break;
            case '6':
                value = 6;
                break;
            case '7':
                value = 7;
                break;
            case '8':
                value = 8;
                break;
            case '9':
                value = 9;
                break;
            case 'T': //T represents card face 10
            case 'J': //jack, queen, and king face cards all have a value of 10
            case 'Q':
            case 'K':
                value = 10;
                break;
            case 'A':
                value = 11; //aces are initialized with value of 11, will be handled in cardDistributor if it needs to change to 1
                break;
            default:
                throw new Exception("Card face character invalid"); //throw an exception if the user inputs an incorrect value
        }
    }

    /**
     * getCardFace returns the face of the current card
     * @return card face as a character
     */
    public char getCardFace() {
        return cardFace;
    }

    /**
     * getValue returns the value of the current card
     * @return value as an integer
     */
    public int getValue() {
        return value;
    }

    /**
     * changeAce will change the value of an ace from 11 to 1
     */
    public void changeAce() {
        if (cardFace == 'A') {
            value = 1;
        }
    }

    /**
     * toString will return the faceValue of the card
     * @return the faceValue of the card as a string
     */
    @Override
    public String toString() {
        return "" + cardFace;
    }
}

/*
*   Arud3K class for custom Arduino functions related to the language:
*   (NOTE: Tageted for the Arduino Uno)
*/

int PinRead(int index, bool bAnalog) {
    if (index >= 0 && index <= 14) {
        if (bAnalog){ return analogRead(index); }
        else { return digitalRead(index); }
    }
}

void PinWrite(int index, bool bAnalog, int writeVal) {
    if (index >= 0 && index <= 14) {
        // Cap writeVal between 0 and 255
        if (writeVal < 0) { writeVal = 0; }
        else if (writeVal > 255) { writeVal = 255; }

        if (bAnalog){ analogWrite(index, writeVal); }
        else { digitalWrite(index, writeVal); }
    }
}

// Switch on index then toggle given pin 
// in bitmask for pin state
void TogglePin(int index, bool bAnalog) {
    if (index >= 0 && index <= 13){
        if (bAnalog == true) {
            switch (index) {
                case 0:
                    PORTC = PORTC ^ B00000001;
                    break;
                case 1:
                    PORTC = PORTC ^ B00000010;
                    break;
                case 2:
                    PORTC = PORTC ^ B00000100;
                    break;
                case 3:
                    PORTC = PORTC ^ B00001000;
                    break;
                case 4:
                    PORTC = PORTC ^ B00010000;
                    break;
                case 5:
                    PORTC = PORTC ^ B00100000;
                    break;
                default:
                    return;
            }
        } else {
            switch (index) {
                case 0:
                    PORTD = PORTD ^ B00000001;
                    break;
                case 1:
                    PORTD = PORTD ^ B00000010;
                    break;
                case 2:
                    PORTD = PORTD ^ B00000100;
                    break;
                case 3:
                    PORTD = PORTD ^ B00001000;
                    break;
                case 4:
                    PORTD = PORTD ^ B00010000;
                    break;
                case 5:
                    PORTD = PORTD ^ B00100000;
                    break;
                case 6:
                    PORTD = PORTD ^ B01000000;
                    break;
                case 7:
                    PORTD = PORTD ^ B10000000;
                    break;
                case 8:
                    PORTB = PORTB ^ B00000001;
                    break;
                case 9:
                    PORTB = PORTB ^ B00000010;
                    break;
                case 10:
                    PORTB = PORTB ^ B00000100;
                    break;
                case 11:
                    PORTB = PORTB ^ B00001000;
                    break;
                case 12:
                    PORTB = PORTB ^ B00010000;
                    break;
                case 13:
                    PORTB = PORTB ^ B00100000;
                    break;
                default:
                    return;
            }
        }
    }
}
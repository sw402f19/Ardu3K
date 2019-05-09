/*
*   Arud3K class for custom Arduino functions related to the language
*/
class Ardu3K {
    // Supports read up to and included an Arduino Mega
    int PinRead(int index, bool bAnalog) {
        if (index >= 0 && index <= 14) {
            if (bAnalog){ return analogRead(index); } 
            else { return digitalRead(index); }
        }
    }

    // Supports write up to and included an Arduino Mega
    void PinWrite(int index, bool bAnalog, int writeVal) {
        if (index >= 0 && index <= 14) {
            if (bAnalog){ analogWrite(index, writeVal); } 
            else { digitalWrite(index, writeVal); }
        }
    }
};
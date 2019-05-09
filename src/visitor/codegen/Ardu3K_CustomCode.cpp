/*
*   Arud3K class for custom Arduino functions related to the language:
*/

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
        // Cap writeVal between 0 and 255
        if (writeVal < 0) { writeVal = 0; }
        else if (writeVal > 255) { writeVal = 255; }

        if (bAnalog){ analogWrite(index, writeVal); }
        else { digitalWrite(index, writeVal); }
    }
}
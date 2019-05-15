/*
*   Arud3K class for custom Arduino functions related to the language:
*   (NOTE: Tageted for the Arduino Uno)
*   The code directly manipulates the bitmasks controlling pins, for better efficiency
*/
class Ardu3K {
public:
    // Return true if it is after or equal to the wait time
    static bool AfterCheck(long startTime, long waitTime) {
        long desiredTime = startTime + waitTime;

        if (desiredTime <= millis()){
            return true;
        } else return false;
    }

    // Return true if it is before or equal to the wait time
    static bool BeforeCheck(long startTime, long waitTime) {
        long desiredTime = startTime + waitTime;

        if (desiredTime => millis()){
            return true;
        } else return false;
    }

    // "Reset" the timer by seting it to current time
    static void ResetTimer(long *timer) { *timer = millis(); }

    // Sets the bitflag representing the data direction of the pin
    static void SetPinMode(short index, bool bAnalog, bool bOutput){
        if (bAnalog) {
            if (index >= 0 && index <= 5) {
                if (bOutput) {
                    DDRC |= (1 << index);
                } else {
                    DDRC &= ~(1 << index); 
                }
            }
        } else {
            if (index >= 0 && index <= 13) {
                if (bOutput) {
                    if (index <= 7) {
                        DDRD |= (1 << index);
                    } else { 
                        DDRB |= (1 << (index - 8));
                    }
                } else {
                    if (index <= 7) {
                        DDRD &= ~(1 << index);
                    } else { 
                        DDRB &= ~(1 << (index - 8));
                    }
                }
            }
        }
    } 

    // Read function following the format of the other pin functions
    static int PinRead(short index, bool bAnalog) {
        if (bAnalog) {
            if (index >= 0 && index <= 5) {
                switch (index) {
                    case 0:
                        return analogRead(A0);
                    case 1:
                        return analogRead(A1);
                    case 2:
                        return analogRead(A2);
                    case 3:
                        return analogRead(A3);
                    case 4:
                        return analogRead(A4);
                    case 5:
                        return analogRead(A5);
                }
            }
        } else {
            if (index >= 0 && index <= 13){
                return digitalRead(index);
            }
        }
        return -1; // INVALID ID
    }

    // Sets the bitflag representing if the pinstate is high or low
    static void PinWrite(short index, bool bAnalog, bool bWriteVal) {
        if (bAnalog) {
            if (index >= 0 && index <= 5) {
                if (bWriteVal) {
                    PORTC |= (1 << index);
                } else {
                    PORTC &= ~(1 << index); 
                }
            }
        } else {
            if (index >= 0 && index <= 13) {
                if (bWriteVal) {
                    if (index <= 7) {
                        PORTD |= (1 << index);
                    } else { 
                        PORTB |= (1 << (index - 8));
                    }
                } else {
                    if (index <= 7) {
                        PORTD &= ~(1 << index);
                    } else { 
                        PORTB &= ~(1 << (index - 8));
                    }
                }
            }
        }
    } 

    // Flip pin-index in bitfield of pin states
    static void TogglePin(short index, bool bAnalog) {
        if (bAnalog) {
            if (index >= 0 && index <= 5) {
                PORTC ^= (1 << index);
            }
        } else {
            if (index >= 0 && index <= 13) {
                if (index <= 7) {
                    PORTD ^= (1 << index);
                } else { 
                    PORTB ^= (1 << (index - 8));
                }
            }
        }
    }
};
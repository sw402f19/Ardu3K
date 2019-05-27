/*
*   Arud3K class for custom Arduino functions related to the language:
*   (NOTE: Tageted for the Arduino Uno)
*   The code directly manipulates the bitmasks controlling pins, for better efficiency
*   DDR* == if pin is input/output mode | PORT* == if pin is HIGH/LOW
*/
class ARDU3k {
public:
    // Return true if it is after or equal to the wait time
    static bool AfterCheck(long clockTime, long waitTime) {
        long desiredTime = clockTime + waitTime;

        if (desiredTime < millis()) {
            return true;
        } else return false;
    }

    // Return true if it is before or equal to the wait time
    static bool BeforeCheck(long clockTime, long waitTime) {
        long desiredTime = clockTime + waitTime;

        if (desiredTime >= millis()) {
            return true;
        } else return false;
    }

    // "Reset" the timer by seting it to current time
    static void ResetClockTime(long *clock) { *clock = millis(); }

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
    // Sets the pin to input mode before reading
    static int PinRead(short index, bool bAnalog) {
        if (bAnalog) {
            if (index >= 0 && index <= 5) {
                DDRC &= ~(1 << index);
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
                if (index <= 7) {
                    DDRD &= ~(1 << index);
                } else {
                    DDRB &= ~(1 << (index - 8));
                }
                return digitalRead(index);
            }
        }
        return -1; // INVALID ID
    }

    // Sets the bitflag representing if the pinstate is high or low
    // Sets the pin to output mode before writing
    static void PinWrite(short index, bool bAnalog, bool bWriteVal) {
        if (bAnalog) {
            if (index >= 0 && index <= 5) {
                DDRC |= (1 << index);
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
                        DDRD |= (1 << index);
                        PORTD |= (1 << index);
                    } else {
                        DDRB |= (1 << (index - 8));
                        PORTB |= (1 << (index - 8));
                    }
                } else {
                    if (index <= 7) {
                        DDRD |= (1 << index);
                        PORTD &= ~(1 << index);
                    } else {
                        DDRB |= (1 << (index - 8));
                        PORTB &= ~(1 << (index - 8));
                    }
                }
            }
        }
    }

    // Flip pin-index in bitfield of pin states
    // Sets the pin to output mode befrore toggle
    static void TogglePin(short index, bool bAnalog) {
        if (bAnalog) {
            if (index >= 0 && index <= 5) {
                DDRC |= (1 << index);
                PORTC ^= (1 << index);
            }
        } else {
            if (index >= 0 && index <= 13) {
                if (index <= 7) {
                    DDRD |= (1 << index);
                    PORTD ^= (1 << index);
                } else {
                    DDRB |= (1 << (index - 8));
                    PORTB ^= (1 << (index - 8));
                }
            }
        }
    }
};

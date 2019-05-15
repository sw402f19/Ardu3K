/*
*   Arud3K class for custom Arduino functions related to the language:
*   (NOTE: Tageted for the Arduino Uno)
*   The code directly manipulates the bitmasks controlling pins, for better efficiency
*/

bool Ardu3K_AfterCheck(long startTime, long waitTime) {
  long desiredTime = startTime + waitTime;

  if (desiredTime < millis()){
      return true;
  } else return false;
}

bool Ardu3K_BeforeCheck(long startTime, long waitTime) {
  long desiredTime = startTime + waitTime;

  if (desiredTime > millis()){
      return true;
  } else return false;
}

void Ardu3K_ResetTimer(long *timer){
    *timer = millis();
}

// Read function following the format of the other pin functions
int Ardu3K_PinRead(int index, bool bAnalog) {
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
void Ardu3K_PinWrite(int index, bool bAnalog, bool bWriteVal) {
    if (bAnalog) {
        if (index >= 0 && index <= 5) {
            if (bWriteVal) {
                PORTC &= (1 << index);
            } else {
                PORTC &= ~(1 << index); 
            }
        }
    } else {
        if (index >= 0 && index <= 13) {
            if (bWriteVal) {
                if (index <= 7) {
                    PORTD &= (1 << index);
                } else { 
                    PORTB &= (1 << (index - 8));
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
void Ardu3K_TogglePin(int index, bool bAnalog) {
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
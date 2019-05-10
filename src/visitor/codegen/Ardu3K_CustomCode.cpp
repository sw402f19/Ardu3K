/*
*   Arud3K class for custom Arduino functions related to the language:
*   (NOTE: Tageted for the Arduino Uno)
*   The code directly manipulates the bitmasks controlling pins, for better efficiency
*/

// Sets pin to read state before reading
int Ardu3K_PinRead(int index, bool bAnalog) {
    if (index >= 0 && index <= 14) {
        if (bAnalog == true) {
            switch (index) {
                case 0:
                    DDRC = DDRC | B00000001;
                    DDRC = DDRC ^ B00000001;
                    return analogRead(A0);
                case 1:
                    DDRC = DDRC | B00000010;
                    DDRC = DDRC ^ B00000010;
                    return analogRead(A1);
                case 2:
                    DDRC = DDRC | B00000100;
                    DDRC = DDRC ^ B00000100;
                    return analogRead(A2);
                case 3:
                    DDRC = DDRC | B00001000;                    
                    DDRC = DDRC ^ B00001000;
                    return analogRead(A3);
                case 4:
                    DDRC = DDRC | B00010000;
                    DDRC = DDRC ^ B00010000;
                    return analogRead(A4);
                case 5:
                    DDRC = DDRC | B00100000;
                    DDRC = DDRC ^ B00100000;
                    return analogRead(A5);
                default:
                    return -1;
            }
        } else {
            switch (index) {
                case 0:
                    DDRD = DDRD | B00000001;
                    DDRD = DDRD ^ B00000001;
                    break;
                case 1:
                    DDRD = DDRD | B00000010;
                    DDRD = DDRD ^ B00000010;
                    break;
                case 2:
                    DDRD = DDRD | B00000100;
                    DDRD = DDRD ^ B00000100;
                    break;
                case 3:
                    DDRD = DDRD | B00001000;
                    DDRD = DDRD ^ B00001000;
                    break;
                case 4:
                    DDRD = DDRD | B00010000;
                    DDRD = DDRD ^ B00010000;
                    break;
                case 5:
                    DDRD = DDRD | B00100000;
                    DDRD = DDRD ^ B00100000;
                    break;
                case 6:
                    DDRD = DDRD | B01000000;
                    DDRD = DDRD ^ B01000000;
                    break;
                case 7:
                    DDRD = DDRD | B10000000;
                    DDRD = DDRD ^ B10000000;
                    break;
                case 8:
                    DDRB = DDRB | B00000001;
                    DDRB = DDRB ^ B00000001;
                    break;
                case 9:
                    DDRB = DDRB | B00000010;
                    DDRB = DDRB ^ B00000010;
                    break;
                case 10:
                    DDRB = DDRB | B00000100;
                    DDRB = DDRB ^ B00000100;
                    break;
                case 11:
                    DDRB = DDRB | B00001000;
                    DDRB = DDRB ^ B00001000;
                    break;
                case 12:
                    DDRB = DDRB | B00010000;
                    DDRB = DDRB ^ B00010000;
                    break;
                case 13:
                    DDRB = DDRB | B00100000;
                    DDRB = DDRB ^ B00100000;
                    break;
                default:
                    return -1;
            }
            return digitalRead(index);
        }
    }
}

// Writes to a pin if it should be on or off
// Sets pin to be in output mode before writing
// Negates bit, by ensuring the bit is 1 and then XOR to flip bit
void Ardu3K_PinWrite(int index, bool bAnalog, bool bWriteVal) {
    if (index >= 0 && index <= 13) {
        if (bAnalog){ 
          switch (index) {
             case 0:
              DDRC = DDRC | B00000001;
              if  (bWriteVal) { PORTC = PORTC | B00000001; }
              else {
                PORTC = PORTC | B00000001;
                PORTC = PORTC ^ B00000001;
              }
              break;
            case 1:
              DDRC = DDRC | B00000010;
              if  (bWriteVal) { PORTC = PORTC | B00000010; }
              else {
                PORTC = PORTC | B00000010;
                PORTC = PORTC ^ B00000010;
              }
              break;
            case 2:
              DDRC = DDRC | B00000100;
              if  (bWriteVal) { PORTC = PORTC | B00000100; }
              else {
                PORTC = PORTC | B00000100;
                PORTC = PORTC ^ B00000100;
              }
              break;
            case 3:
              DDRC = DDRC | B00001000;
              if  (bWriteVal) { PORTC = PORTC | B00001000; }
              else {
                PORTC = PORTC | B00001000;
                PORTC = PORTC ^ B00001000;
              }
              break;
            case 4:
              DDRC = DDRC | B00010000;
              if  (bWriteVal) { PORTC = PORTC | B00010000; }
              else {
                PORTC = PORTC | B00010000;
                PORTC = PORTC ^ B00010000;
              }
              break;
            case 5:
              DDRC = DDRC | B00100000;
              if  (bWriteVal) { PORTC = PORTC | B00100000; }
              else {
                PORTC = PORTC | B00100000;
                PORTC = PORTC ^ B00100000;
              }
              break;
            default:
              return;
          }
        } else { 
          switch (index) {
             case 0:
              DDRD = DDRD | B00000001;
              if  (bWriteVal) { PORTD = PORTD | B00000001; }
              else {
                PORTD = PORTD | B00000001;
                PORTD = PORTD ^ B00000001;
              }
              break;
            case 1:
              DDRD = DDRD | B00000010;
              if  (bWriteVal) { PORTD = PORTD | B00000010; }
              else {
                PORTD = PORTD | B00000010;
                PORTD = PORTD ^ B00000010;
              }
              break;
            case 2:
              DDRD = DDRD | B00000100;
              if  (bWriteVal) { PORTD = PORTD | B00000100; }
              else {
                PORTD = PORTD | B00000100;
                PORTD = PORTD ^ B00000100;
              }
              break;
            case 3:
              DDRD = DDRD | B00001000;
              if  (bWriteVal) { PORTD = PORTD | B00001000; }
              else {
                PORTD = PORTD | B00001000;
                PORTD = PORTD ^ B00001000;
              }
              break;
            case 4:
              DDRD = DDRD | B00010000;
              if  (bWriteVal) { PORTD = PORTD | B00010000; }
              else {
                PORTD = PORTD | B00010000;
                PORTD = PORTD ^ B00010000;
              }
              break;
            case 5:
              DDRD = DDRD | B00100000;
              if  (bWriteVal) { PORTD = PORTD | B00100000; }
              else {
                PORTD = PORTD | B00100000;
                PORTD = PORTD ^ B00100000;
              }
              break;
            case 6:
              DDRD = DDRD | B01000000;
              if  (bWriteVal) { PORTD = PORTD | B01000000; }
              else {
                PORTD = PORTD | B01000000;
                PORTD = PORTD ^ B01000000;
              }
              break;
            case 7:
              DDRD = DDRD | B10000000;
              if  (bWriteVal) { PORTD = PORTD | B10000000; }
              else {
                PORTD = PORTD | B10000000;
                PORTD = PORTD ^ B10000000;
              }
              break;
            case 8:
              DDRB = DDRB | B00000001;
              if  (bWriteVal) { PORTB = PORTB | B00000001; }
              else {
                PORTB = PORTB | B00000001;
                PORTB = PORTB ^ B00000001;
              }
              break;
            case 9:
              DDRB = DDRB | B00000010;
              if  (bWriteVal) { PORTB = PORTB | B00000010; }
              else {
                PORTB = PORTB | B00000010;
                PORTB = PORTB ^ B00000010;
              }
              break;
            case 10:
              DDRB = DDRB | B00000100;
              if  (bWriteVal) { PORTB = PORTB | B00000100; }
              else {
                PORTB = PORTB | B00000100;
                PORTB = PORTB ^ B00000100;
              }
              break;
            case 11:
              DDRB = DDRB | B00001000;
              if  (bWriteVal) { PORTB = PORTB | B00001000; }
              else {
                PORTB = PORTB | B00001000;
                PORTB = PORTB ^ B00001000;
              }
              break;
            case 12:
              DDRB = DDRB | B00010000;
              if  (bWriteVal) { PORTB = PORTB | B00010000; }
              else {
                PORTB = PORTB | B00010000;
                PORTB = PORTB ^ B00010000;
              }
              break;
            case 13:
              DDRB = DDRB | B00100000;
              if  (bWriteVal) { PORTB = PORTB | B00100000; }
              else {
                PORTB = PORTB | B00100000;
                PORTB = PORTB ^ B00100000;
              }
              break;
            default:
              return;
          } 
        }
    }
}

// Switch on index then toggle given pin 
// in bitmask for pin state
void Ardu3K_TogglePin(int index, bool bAnalog) {
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
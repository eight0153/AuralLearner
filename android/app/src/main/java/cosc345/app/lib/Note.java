package cosc345.app.lib;

/**
 * Represents a musical note.
 */
public class Note {
    public static final String[] NOTE_NAMES = {
            "C2", "C#2", "D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2",
            "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3",
            "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4",
            "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5",
            "C6", "C#6", "D6", "D#6", "E6", "F6", "F#6", "G6", "G#6", "A6", "A#6", "B6"};

    public static final String[] NOTE_NAMES_FLATS = {
            "C2", "Db2", "D2", "Eb2", "E2", "F2", "Gb2", "G2", "Ab2", "A2", "Bb2", "B2",
            "C3", "Db3", "D3", "Eb3", "E3", "F3", "Gb3", "G3", "Ab3", "A3", "Bb3", "B3",
            "C4", "Db4", "D4", "Eb4", "E4", "F4", "Gb4", "G4", "Ab4", "A4", "Bb4", "B4",
            "C5", "Db5", "D5", "Eb5", "E5", "F5", "Gb5", "G5", "Ab5", "A5", "Bb5", "B5",
            "C6", "Db6", "D6", "Eb6", "E6", "F6", "Gb6", "G6", "Ab6", "A6", "Bb6", "B6"};

    public static final int A4_INDEX = 33;
    public static final double A4_FREQUENCY = 440.0; // in Hertz
    private static final int A4_OCTAVE = 4;
    private static final int HALF_STEPS_IN_OCTAVE_BELOW_A4 = 9; // before the octave changes.
    private static final int NUM_HALF_STEPS = 12; // per octave.
    private static final int NUM_CENTS = NUM_HALF_STEPS * 100; // per octave.
    private static final double MIN_FREQUENCY = 63.57; // C2 minus 49 cents
    private static final double MAX_FREQUENCY = 2034.0; // B6 plus 50 cents

    private final int nameIndex;
    private final double frequency;
    private final int halfStepDistance;
    private final int octave;
    private final int cents;

    /**
     * Create a musical note based on a frequency.
     *
     * @param frequency the frequency (in Hertz) to use.
     */
    public Note(double frequency) {
        if (frequency < MIN_FREQUENCY || frequency > MAX_FREQUENCY)
            throw new IllegalArgumentException();

        // Formulas found at http://newt.phys.unsw.edu.au/jw/notes.html
        int hsDist = halfStepDistance(frequency);
        double refFreq = frequency(hsDist);
        int centDist = (int) Math.round(NUM_CENTS * Math.log(frequency / refFreq) / Math.log(2.0));

        this.nameIndex = A4_INDEX + hsDist;
        this.frequency = frequency;
        this.halfStepDistance = hsDist;
        this.octave = octave(hsDist);
        this.cents = centDist % 100;
    }

    /**
     * Create a musical note from a string.
     *
     * @param name the name of the note that follows the format (Note Letter)[#|b](Octave).
     *             For example a note name may look like: A#3 or Db4.
     */
    public Note(String name) {
        int noteIndex = Utilities.indexOf(name, NOTE_NAMES);

        if (noteIndex < 0)
            noteIndex = Utilities.indexOf(name, NOTE_NAMES_FLATS);

        if (noteIndex < 0)
            throw new IllegalArgumentException("Invalid Note Name");

        nameIndex = noteIndex;
        halfStepDistance = noteIndex - A4_INDEX;
        frequency = frequency(halfStepDistance);
        octave = octave(halfStepDistance);
        cents = 0;
    }

    /**
     * Calculate the octave of a note based on its distance in half steps from A4.
     *
     * @param halfStepDistance the distance in half steps of the note from A4.
     * @return the octave of a note.
     */
    public static int octave(int halfStepDistance) {
        // 1.0 is here to avoid calculation errors due to integer division/rounding.
        return (int) (A4_OCTAVE + 1.0 * (halfStepDistance + HALF_STEPS_IN_OCTAVE_BELOW_A4) / NUM_HALF_STEPS);
    }

    /**
     * Calculate the distance in half steps of a note from A4 based on a given frequency.
     *
     * @param frequency the frequency of the note.
     * @return the distance in half steps from A4.
     */
    public static int halfStepDistance(double frequency) {
        return (int) Math.round(NUM_HALF_STEPS * Math.log(frequency / A4_FREQUENCY) / Math.log(2.0));
    }

    /**
     * Calculate the frequency of a note given its distance in half steps from A4.
     *
     * @param halfStepsDistance the number of half steps from A4
     * @return the frequency of the note in Hertz. This is the frequency assuming perfect pitch.
     */
    public static double frequency(int halfStepsDistance) {
        return Math.pow(2, 1.0 * halfStepsDistance / NUM_HALF_STEPS) * A4_FREQUENCY;
    }

    /**
     * Getter for the frequency of a note.
     *
     * @return the frequency of a note.
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * Getter for the octave of a note.
     *
     * @return the octave of the note.
     */
    public int getOctave() {
        return octave;
    }

    /**
     * Getter for the cents of a note.
     *
     * @return the cents of the note.
     */
    public int getCents() {
        return cents;
    }

    /**
     * Get the name of a note.
     *
     * @return the name of the note.
     */
    public String getName() {
        return NOTE_NAMES[nameIndex];
    }

    /**
     * Get the name of a note.
     *
     * @param useFlats whether or not to use flats (b) or sharps (#) in the name.
     * @return the name of the note.
     */
    public String getName(boolean useFlats) {
        if (useFlats) {
            return NOTE_NAMES_FLATS[nameIndex];
        } else {
            return getName();
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}

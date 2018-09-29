package cosc345.app;

import org.junit.Test;

import cosc345.app.model.Note;
import cosc345.app.model.Scale;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Scale class.
 */
public class ScaleUnitTest {
    @Test
    public void testMajorScale() {
        final Note[] expected = new Note[]{
                new Note("C4"),
                new Note("D4"),
                new Note("E4"),
                new Note("F4"),
                new Note("G4"),
                new Note("A4"),
                new Note("B4"),
                new Note("C5")
        };

        final Scale scale = new Scale(expected[0], Scale.ScaleType.MAJOR);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(scale.notes[i], expected[i]);
        }
    }

    @Test
    public void testNaturalMinorScale() {
        final Note[] expected = new Note[]{
                new Note("C4"),
                new Note("D4"),
                new Note("Eb4"),
                new Note("F4"),
                new Note("G4"),
                new Note("Ab4"),
                new Note("Bb4"),
                new Note("C5")
        };

        final Scale scale = new Scale(expected[0], Scale.ScaleType.NATURAL_MINOR);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(
                    scale.notes[i].getName(true),
                    expected[i].getName(true));
        }
    }

    @Test
    public void testHarmonicMinorScale() {
        final Note[] expected = new Note[]{
                new Note("C4"),
                new Note("D4"),
                new Note("Eb4"),
                new Note("F4"),
                new Note("G4"),
                new Note("Ab4"),
                new Note("B4"),
                new Note("C5")
        };

        final Scale scale = new Scale(expected[0], Scale.ScaleType.HARMONIC_MINOR);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(
                    scale.notes[i].getName(true),
                    expected[i].getName(true));
        }
    }

    @Test
    public void testMelodicMinorScale() {
        final Note[] expected = new Note[]{
                new Note("C4"),
                new Note("D4"),
                new Note("Eb4"),
                new Note("F4"),
                new Note("G4"),
                new Note("A4"),
                new Note("B4"),
                new Note("C5")
        };

        final Scale scale = new Scale(expected[0], Scale.ScaleType.MELODIC_MINOR);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(
                    scale.notes[i].getName(true),
                    expected[i].getName(true));
        }
    }

    @Test
    public void testMajorPentatonicScale() {
        final Note[] expected = new Note[]{
                new Note("C4"),
                new Note("D4"),
                new Note("E4"),
                new Note("G4"),
                new Note("A4"),
                new Note("C5")
        };

        final Scale scale = new Scale(expected[0], Scale.ScaleType.MAJOR_PENTATONIC);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(
                    scale.notes[i].getName(true),
                    expected[i].getName(true));
        }
    }

    @Test
    public void testMinorPentatonicScale() {
        final Note[] expected = new Note[]{
                new Note("C4"),
                new Note("Eb4"),
                new Note("F4"),
                new Note("G4"),
                new Note("Bb4"),
                new Note("C5")
        };

        final Scale scale = new Scale(expected[0], Scale.ScaleType.MINOR_PENTATONIC);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(
                    scale.notes[i].getName(true),
                    expected[i].getName(true));
        }
    }
}


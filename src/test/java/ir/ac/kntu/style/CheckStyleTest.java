package ir.ac.kntu.style;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.DefaultLogger;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.AutomaticBean;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import net.sourceforge.pmd.cpd.CPD;
import net.sourceforge.pmd.cpd.CPDConfiguration;
import net.sourceforge.pmd.cpd.LanguageFactory;
import net.sourceforge.pmd.cpd.Match;
import org.junit.jupiter.api.*;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Shayan Daneshvar
 */
public class CheckStyleTest {
    private static final List<File> FILES = new ArrayList<>();
    private static int score = 0;

    @BeforeAll
    public static void prepare() throws MalformedURLException {
        final File root = new File(new File("src").toURI()
                .toURL().getPath());
        System.out.println("Selecting root as " + root);
        listFiles(FILES, root, "java");
        System.out.println("Found " + FILES.size() + " Java source files.");
    }

    @AfterAll
    public static void showScore() {
        System.out.println(" ____________________");
        System.out.println("|                    |");
        System.out.printf("|    Score : %2d/40   |\n", score);
        System.out.println("|____________________|");
    }

    @Test
    @Tag("Blocks")
    @DisplayName("Checkstyle for Blocks")
    public void testCheckStyleBlocks() {
        testCheckStyle("blocks.xml");
        score += 10;
    }

    @Test
    @Tag("NamingConventions")
    @DisplayName("Checkstyle for Naming Conventions")
    public void testCheckStyleNaming() {
        testCheckStyle("naming.xml");
        score += 10;
    }

    @Test
    @Tag("Imports")
    @DisplayName("Checkstyle for Imports")
    public void testCheckStyleImports() {
        testCheckStyle("imports.xml");
        score += 10;
    }

    public void testCheckStyle(final String config) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final AuditListener listener = new DefaultLogger(baos, AutomaticBean
                .OutputStreamOptions.NONE);

        final File conf = new File("src/test/java/ir/ac/kntu/style/" +
                config);
        InputSource inputSource = null;
        try {
            inputSource = new InputSource(new FileInputStream(conf));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CheckStyleTest.class.getName()).log(Level.SEVERE
                    , "Error"
                    , ex);
        }
        Configuration configuration = null;
        try {
            configuration = ConfigurationLoader.loadConfiguration(inputSource,
                    new PropertiesExpander(System.getProperties()),
                    ConfigurationLoader.IgnoredModulesOptions.OMIT);
        } catch (CheckstyleException ex) {
            Logger.getLogger(CheckStyleTest.class.getName()).log(Level.WARNING,
                    null, ex);
        }
        final Checker checker = new Checker();
        checker.setModuleClassLoader(Checker.class.getClassLoader());
        try {
            assert configuration != null;
            checker.configure(configuration);
        } catch (CheckstyleException ex) {
            Logger.getLogger(CheckStyleTest.class.getName()).log(Level.WARNING,
                    "Error", ex);
        }
        checker.addListener(listener);
        int errors = 0;
        try {
            errors = checker.process(FILES);
        } catch (CheckstyleException ex) {
            Logger.getLogger(CheckStyleTest.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        System.out.println("Found " + errors + " check style errors.");
        System.out.println(baos.toString());
        assertEquals(0, errors, errors + " check style errors "
                + "found. " + baos.toString());
        checker.destroy();
    }

    @Test
    @Tag("CPD")
    @DisplayName("Copy-Paste Detector")
    public void testCPD() {
        final CPDConfiguration cpdConfiguration = new CPDConfiguration();
        cpdConfiguration.setLanguage(LanguageFactory.createLanguage("java"));
        cpdConfiguration.setMinimumTileSize(60);
        cpdConfiguration.setFailOnViolation(true);
        final CPD copyPasteDetector = new CPD(cpdConfiguration);
        try {
            copyPasteDetector.add(FILES);
        } catch (IOException e) {
            e.printStackTrace();
        }
        copyPasteDetector.go();
        final Iterator<Match> matches = copyPasteDetector.getMatches();
        assertFalse(matches.hasNext(), "An Error Occurred!");
        score += 10;
    }

    static void listFiles(final List<File> files, final File folder,
                          final String extension) {
        if (folder.canRead()) {
            if (folder.isDirectory()) {
                for (final File file : Objects.
                        requireNonNull(folder.listFiles())) {
                    listFiles(files, file, extension);
                }
            } else if (folder.toString().endsWith("." + extension) &&
                    !folder.toString().contains("help.txt")) {
                files.add(folder);
            }
        }
    }
}
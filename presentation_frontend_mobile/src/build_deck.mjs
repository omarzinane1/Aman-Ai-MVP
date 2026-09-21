import fs from "node:fs";
import path from "node:path";
import {
  Presentation,
  PresentationFile,
  row,
  column,
  grid,
  panel,
  text,
  shape,
  rule,
  fill,
  fixed,
  hug,
  wrap,
  fr,
  auto,
} from "@oai/artifact-tool";
import { stroke } from "@oai/artifact-tool/presentation-jsx";

const SLIDE_W = 1920;
const SLIDE_H = 1080;
const ROOT = path.resolve(".");
const OUT = path.join(ROOT, "output", "output.pptx");
const SCRATCH = path.join(ROOT, "scratch");

fs.mkdirSync(path.dirname(OUT), { recursive: true });
fs.mkdirSync(SCRATCH, { recursive: true });

const C = {
  navy: "#0B1F3A",
  blue: "#2563EB",
  cyan: "#38BDF8",
  mint: "#14B8A6",
  red: "#EF4444",
  amber: "#F59E0B",
  ink: "#111827",
  muted: "#64748B",
  light: "#F8FAFC",
  panel: "#FFFFFF",
  line: "#D7E2F0",
  softBlue: "#EAF2FF",
  softCyan: "#E7F8FF",
  softMint: "#E8FBF7",
  softRed: "#FEF2F2",
  softAmber: "#FFF7E6",
  softGray: "#F1F5F9",
};

const titleStyle = {
  typeface: "Aptos Display",
  fontSize: 56,
  bold: true,
  color: C.ink,
};

const subtitleStyle = {
  typeface: "Aptos",
  fontSize: 22,
  color: C.muted,
  lineSpacing: 1.08,
};

const eyebrowStyle = {
  typeface: "Aptos",
  fontSize: 15,
  bold: true,
  color: C.blue,
};

const smallStyle = {
  typeface: "Aptos",
  fontSize: 18,
  color: C.muted,
};

const presentation = Presentation.create({
  slideSize: { width: SLIDE_W, height: SLIDE_H },
});

function compose(slide, child) {
  slide.compose(child, {
    frame: { left: 0, top: 0, width: SLIDE_W, height: SLIDE_H },
    baseUnit: 8,
  });
}

function slideShell(title, subtitle, bodyChildren, options = {}) {
  return panel(
    {
      name: `${options.name || "slide"}-background`,
      width: fill,
      height: fill,
      fill: C.light,
      padding: { x: 78, y: 58 },
      materialize: true,
    },
    column(
      {
        name: `${options.name || "slide"}-root`,
        width: fill,
        height: fill,
        gap: 26,
      },
      [
        row(
          {
            name: `${options.name || "slide"}-header`,
            width: fill,
            height: hug,
            align: "center",
            gap: 18,
          },
          [
            column({ width: fill, height: fixed(174), gap: 10, justify: "center" }, [
              text(options.eyebrow || "Frontend mobile Android", {
                name: `${options.name || "slide"}-eyebrow`,
                width: fill,
                height: hug,
                style: eyebrowStyle,
              }),
              text(title, {
                name: `${options.name || "slide"}-title`,
                width: fill,
                height: hug,
                style: titleStyle,
              }),
              text(subtitle, {
                name: `${options.name || "slide"}-subtitle`,
                width: wrap(1180),
                height: hug,
                style: subtitleStyle,
              }),
            ]),
            panel(
              {
                name: `${options.name || "slide"}-badge`,
                width: fixed(190),
                height: fixed(48),
                fill: C.navy,
                borderRadius: 24,
                padding: { x: 20, y: 10 },
                align: "center",
                justify: "center",
                materialize: true,
              },
              text(options.badge || "AMAN-AI", {
                width: fill,
                height: hug,
                style: {
                  typeface: "Aptos",
                  fontSize: 18,
                  bold: true,
                  color: "#FFFFFF",
                  alignment: "center",
                },
              }),
            ),
          ],
        ),
        rule({
          name: `${options.name || "slide"}-rule`,
          width: fixed(210),
          stroke: C.blue,
          weight: 5,
        }),
        ...bodyChildren,
      ],
    ),
  );
}

function techCard(code, title, desc, fillColor, accent) {
  return panel(
    {
      name: `tech-card-${code}`,
      width: fill,
      height: fixed(185),
      fill: fillColor,
      line: stroke(`1 ${C.line}`),
      borderRadius: 24,
      padding: { x: 22, y: 20 },
      materialize: true,
    },
    row({ width: fill, height: fill, gap: 18, align: "center" }, [
      panel(
        {
          width: fixed(62),
          height: fixed(62),
          fill: accent,
          borderRadius: 31,
          align: "center",
          justify: "center",
          materialize: true,
        },
        text(code, {
          width: fill,
          height: hug,
          style: {
            typeface: "Aptos",
            fontSize: 19,
            bold: true,
            color: "#FFFFFF",
            alignment: "center",
          },
        }),
      ),
      column({ width: fill, height: hug, gap: 8 }, [
        text(title, {
          width: fill,
          height: hug,
          style: {
            typeface: "Aptos Display",
            fontSize: 27,
            bold: true,
            color: C.ink,
          },
        }),
        text(desc, {
          width: fill,
          height: hug,
          style: {
            typeface: "Aptos",
            fontSize: 18,
            color: C.muted,
            lineSpacing: 1.08,
          },
        }),
      ]),
    ]),
  );
}

function flowNode(label, tone = "white", width = 260) {
  const palette = {
    white: [C.panel, C.line, C.ink],
    blue: [C.softBlue, "#B7CEF9", C.navy],
    cyan: [C.softCyan, "#A8E6FF", C.navy],
    mint: [C.softMint, "#9BE2D6", C.navy],
    red: [C.softRed, "#FECACA", C.ink],
    amber: [C.softAmber, "#FBD38D", C.ink],
  }[tone];
  return panel(
    {
      width: fixed(width),
      height: fixed(58),
      fill: palette[0],
      line: stroke(`1 ${palette[1]}`),
      borderRadius: 18,
      padding: { x: 12, y: 14 },
      align: "center",
      justify: "center",
      materialize: true,
    },
    text(label, {
      width: fill,
      height: hug,
      style: {
        typeface: "Aptos",
        fontSize: 17,
        bold: true,
        color: palette[2],
        alignment: "center",
      },
    }),
  );
}

function arrow(label = "→") {
  return text(label, {
    width: fixed(46),
    height: fixed(42),
    style: {
      typeface: "Aptos Display",
      fontSize: 30,
      bold: true,
      color: C.blue,
      alignment: "center",
    },
  });
}

function comparisonColumn(title, items, tone) {
  const isCompose = tone === "compose";
  return panel(
    {
      width: fill,
      height: fixed(410),
      fill: isCompose ? C.softBlue : C.softGray,
      line: stroke(`1 ${isCompose ? "#B7CEF9" : C.line}`),
      borderRadius: 26,
      padding: { x: 34, y: 30 },
      materialize: true,
    },
    column({ width: fill, height: fill, gap: 20 }, [
      row({ width: fill, height: hug, gap: 14, align: "center" }, [
        panel(
          {
            width: fixed(50),
            height: fixed(50),
            fill: isCompose ? C.blue : "#94A3B8",
            borderRadius: 25,
            align: "center",
            justify: "center",
            materialize: true,
          },
          text(isCompose ? "JC" : "XML", {
            width: fill,
            height: hug,
            style: {
              typeface: "Aptos",
              fontSize: isCompose ? 16 : 13,
              bold: true,
              color: "#FFFFFF",
              alignment: "center",
            },
          }),
        ),
        text(title, {
          width: fill,
          height: hug,
          style: {
            typeface: "Aptos Display",
            fontSize: 34,
            bold: true,
            color: C.ink,
          },
        }),
      ]),
      column(
        { width: fill, height: fill, gap: 14 },
        items.map((item) =>
          row({ width: fill, height: hug, gap: 12, align: "center" }, [
            shape({
              geometry: "ellipse",
              width: fixed(10),
              height: fixed(10),
              fill: isCompose ? C.blue : "#94A3B8",
            }),
            text(item, {
              width: fill,
              height: hug,
              style: {
                typeface: "Aptos",
                fontSize: 22,
                color: C.ink,
                lineSpacing: 1.05,
              },
            }),
          ]),
        ),
      ),
    ]),
  );
}

// Slide 1
{
  const slide = presentation.slides.add();
  compose(
    slide,
    slideShell(
      "Technologies Frontend Mobile",
      "Un socle Android moderne pour construire une interface claire, responsive et maintenable.",
      [
        grid(
          {
            name: "tech-grid",
            width: fill,
            height: fill,
            columns: [fr(1), fr(1), fr(1)],
            rows: [fr(1), auto, fr(1)],
            columnGap: 24,
            rowGap: 24,
          },
          [
            techCard("K", "Kotlin", "Langage principal Android", C.panel, C.navy),
            techCard("UI", "Jetpack Compose", "Création moderne de l'interface utilisateur", C.softBlue, C.blue),
            techCard("M3", "Material 3", "Couleurs, typographie, surfaces et boutons", C.panel, C.mint),
            panel(
              {
                name: "frontend-hub",
                columnSpan: 3,
                width: fill,
                height: fixed(150),
                fill: C.navy,
                borderRadius: 32,
                padding: { x: 40, y: 28 },
                align: "center",
                justify: "center",
                materialize: true,
              },
              column({ width: fill, height: hug, gap: 8 }, [
                text("Frontend Mobile Android", {
                  width: fill,
                  height: hug,
                  style: {
                    typeface: "Aptos Display",
                    fontSize: 44,
                    bold: true,
                    color: "#FFFFFF",
                    alignment: "center",
                  },
                }),
                text("Interface utilisateur • Navigation • Expérience mobile", {
                  width: fill,
                  height: hug,
                  style: {
                    typeface: "Aptos",
                    fontSize: 22,
                    color: "#BFDBFE",
                    alignment: "center",
                  },
                }),
              ]),
            ),
            techCard("NAV", "Navigation Compose", "Navigation entre les écrans", C.softCyan, C.cyan),
            techCard("PRE", "Compose Preview", "Visualisation rapide des écrans", C.panel, C.amber),
            techCard("AS", "Android Studio / Gradle", "Développement et build du projet mobile", C.panel, C.blue),
          ],
        ),
      ],
      { name: "slide1", badge: "Mobile UI" },
    ),
  );
  slide.speakerNotes.setText(
    "Dans ma partie du projet, je présente uniquement le frontend mobile Android. J'ai utilisé Kotlin comme langage principal, avec Jetpack Compose pour construire les interfaces de manière moderne et déclarative. Material 3 apporte une base visuelle cohérente pour les couleurs, la typographie, les surfaces et les boutons. Navigation Compose permet d'organiser le passage entre les écrans. Compose Preview m'aide à visualiser rapidement les interfaces dans Android Studio, tandis que Gradle gère la structure et le build du projet mobile.",
  );
}

// Slide 2
{
  const slide = presentation.slides.add();
  compose(
    slide,
    slideShell(
      "Navigation & User Flow réel",
      "Le parcours est centralisé dans AppNavGraph et construit autour d'un accès discret vers la partie sécurité.",
      [
        row({ name: "flow-main", width: fill, height: fill, gap: 24, align: "center" }, [
          column({ width: fixed(360), height: fill, gap: 14, align: "center", justify: "center" }, [
            text("1. Lancement", {
              width: fill,
              height: hug,
              style: { typeface: "Aptos", fontSize: 20, bold: true, color: C.blue },
            }),
            flowNode("SplashScreen", "blue", 300),
            text("↓", { width: fill, height: fixed(32), style: { fontSize: 26, bold: true, color: C.blue, alignment: "center" } }),
            flowNode("WeatherForecastScreen", "cyan", 300),
            flowNode("WeatherHomeScreen", "cyan", 300),
            text("Safety / Settings", {
              width: fill,
              height: hug,
              style: { typeface: "Aptos", fontSize: 17, color: C.muted, alignment: "center" },
            }),
          ]),
          arrow(),
          column({ width: fixed(405), height: fill, gap: 13, align: "center", justify: "center" }, [
            text("2. Accès & configuration", {
              width: fill,
              height: hug,
              style: { typeface: "Aptos", fontSize: 20, bold: true, color: C.blue },
            }),
            flowNode("SecretAccessPinScreen", "amber", 340),
            flowNode("OnboardingScreen", "white", 340),
            flowNode("PermissionsSetupScreen", "white", 340),
            flowNode("TrustContactsScreen", "white", 340),
            flowNode("AlertDelayConfigScreen", "white", 340),
          ]),
          arrow(),
          column({ width: fill, height: fill, gap: 16, align: "center", justify: "center" }, [
            text("3. Dashboard & parcours d'urgence", {
              width: fill,
              height: hug,
              style: { typeface: "Aptos", fontSize: 20, bold: true, color: C.blue },
            }),
            flowNode("SafetyDashboardScreen", "mint", 390),
            row({ width: fill, height: hug, gap: 14, align: "center", justify: "center" }, [
              column({ width: fixed(265), height: hug, gap: 10, align: "center" }, [
                flowNode("SOS", "red", 230),
                text("↓", { width: fill, height: fixed(28), style: { fontSize: 22, bold: true, color: C.red, alignment: "center" } }),
                flowNode("AlertDetectedScreen", "red", 230),
                flowNode("AlertCountdownScreen", "red", 230),
                flowNode("AlertSentStatusScreen", "red", 230),
              ]),
              column({ width: fixed(265), height: hug, gap: 10, align: "center" }, [
                flowNode("Cancel", "white", 230),
                text("↘", { width: fill, height: fixed(28), style: { fontSize: 22, bold: true, color: C.muted, alignment: "center" } }),
                flowNode("AlertCancelledScreen", "white", 230),
                flowNode("AlertHistoryScreen", "blue", 230),
                flowNode("SafetySettingsScreen", "blue", 230),
              ]),
            ]),
            panel(
              {
                width: fill,
                height: fixed(74),
                fill: "#FFFFFF",
                line: stroke(`1 ${C.line}`),
                borderRadius: 18,
                padding: { x: 18, y: 14 },
                materialize: true,
              },
              text("Routes déclarées hors flow principal clair : DetectionStatusScreen, PanicQuickEraseScreen, SmartwatchSyncScreen", {
                width: fill,
                height: hug,
                style: { typeface: "Aptos", fontSize: 17, color: C.muted, alignment: "center" },
              }),
            ),
          ]),
        ]),
      ],
      { name: "slide2", badge: "User Flow" },
    ),
  );
  slide.speakerNotes.setText(
    "La navigation réelle est définie dans AppNavGraph. L'application démarre sur SplashScreen, puis redirige automatiquement vers WeatherForecastScreen. Depuis l'interface météo, l'utilisateur peut ouvrir WeatherHomeScreen ou accéder à la partie sécurité via SecretAccessPinScreen. Après validation du PIN, le parcours passe par OnboardingScreen, PermissionsSetupScreen, TrustContactsScreen et AlertDelayConfigScreen. L'écran principal devient ensuite SafetyDashboardScreen. Depuis ce dashboard, l'utilisateur peut déclencher un SOS, consulter l'historique ou accéder aux paramètres. Le parcours d'urgence prévoit la confirmation, le compte à rebours, l'alerte envoyée ou l'annulation.",
  );
}

// Slide 3
{
  const slide = presentation.slides.add();
  compose(
    slide,
    slideShell(
      "Pourquoi Jetpack Compose ?",
      "Un choix adapté aux interfaces riches : cartes, dashboards, alertes, compte à rebours et bottom bars.",
      [
        row({ name: "compare", width: fill, height: hug, gap: 26, align: "center" }, [
          comparisonColumn("XML classique", [
            "Layouts séparés",
            "Code plus fragmenté",
            "État plus complexe",
            "Aperçu moins direct",
          ], "xml"),
          comparisonColumn("Jetpack Compose", [
            "UI écrite en Kotlin",
            "Code déclaratif et lisible",
            "Composants réutilisables",
            "Preview rapide dans Android Studio",
          ], "compose"),
        ]),
        panel(
          {
            name: "project-examples",
            width: fill,
            height: fixed(238),
            fill: C.navy,
            borderRadius: 30,
            padding: { x: 34, y: 28 },
            materialize: true,
          },
          grid(
            {
              width: fill,
              height: fill,
              columns: [fr(0.9), fr(1.1)],
              columnGap: 36,
              alignItems: "center",
            },
            [
              column({ width: fill, height: fixed(170), gap: 8, justify: "center" }, [
                text("Exemples réels du projet", {
                  width: fill,
                  height: hug,
                  style: {
                    typeface: "Aptos Display",
                    fontSize: 31,
                    bold: true,
                    color: "#FFFFFF",
                  },
                }),
                text("Screens : WeatherForecastScreen, SafetyDashboardScreen, SecretAccessPinScreen, AlertCountdownScreen", {
                  width: fill,
                  height: hug,
                  style: {
                    typeface: "Aptos",
                    fontSize: 19,
                    color: "#C7D2FE",
                    lineSpacing: 1.08,
                  },
                }),
              ]),
              column({ width: fill, height: fixed(170), gap: 10, justify: "center" }, [
                text("Composables : PermissionCard, PrimaryActionCard, CountdownCircle, SafetyBottomNav", {
                  width: fill,
                  height: hug,
                  style: {
                    typeface: "Aptos",
                    fontSize: 19,
                    color: "#E0F2FE",
                    lineSpacing: 1.08,
                  },
                }),
                text("UI : Box, Column, Row, Text, Surface, Canvas, Spacer, clickable, verticalScroll", {
                  width: fill,
                  height: hug,
                  style: {
                    typeface: "Aptos",
                    fontSize: 19,
                    color: "#E0F2FE",
                    lineSpacing: 1.08,
                  },
                }),
              ]),
            ],
          ),
        ),
        text("Conclusion : une interface moderne, modulaire et facile à maintenir pour une expérience mobile fluide.", {
          width: fill,
          height: hug,
          style: { typeface: "Aptos Display", fontSize: 25, bold: true, color: C.ink, alignment: "center" },
        }),
      ],
      { name: "slide3", badge: "Compose" },
    ),
  );
  slide.speakerNotes.setText(
    "Jetpack Compose est adapté à ce projet parce que l'application contient plusieurs interfaces riches : météo, dashboard sécurité, paramètres, alertes et compte à rebours. Avec Compose, l'interface est écrite directement en Kotlin, ce qui réduit la séparation entre XML et logique UI. Le code devient plus lisible, plus modulaire et plus rapide à faire évoluer. Dans le projet, les écrans utilisent des composants comme Box, Column, Row, Text, Surface, Canvas, Spacer, clickable et verticalScroll. Certains états d'interface, comme le PIN ou le compte à rebours, sont gérés simplement avec remember et LaunchedEffect. Ma partie apporte donc une interface moderne, une navigation claire et une expérience utilisateur fluide.",
  );
}

const pptxBlob = await PresentationFile.exportPptx(presentation);
await pptxBlob.save(OUT);

for (let i = 0; i < presentation.slides.count; i += 1) {
  const slide = presentation.slides.getItem(i);
  const png = await slide.export({ format: "png" });
  fs.writeFileSync(path.join(SCRATCH, `slide-${i + 1}.png`), Buffer.from(await png.arrayBuffer()));
  const layout = await slide.export({ format: "layout" });
  fs.writeFileSync(path.join(SCRATCH, `slide-${i + 1}.layout.json`), JSON.stringify(layout, null, 2), "utf8");
}

console.log(JSON.stringify({
  pptx: OUT,
  previews: [1, 2, 3].map((n) => path.join(SCRATCH, `slide-${n}.png`)),
}, null, 2));

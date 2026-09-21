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
const OUT = path.join(ROOT, "output", "aman_ai_architecture_motion_5min.pptx");
const SCRATCH = path.join(ROOT, "scratch");

fs.mkdirSync(path.dirname(OUT), { recursive: true });
fs.mkdirSync(SCRATCH, { recursive: true });

const C = {
  ink: "#101827",
  navy: "#152238",
  blue: "#2563EB",
  cyan: "#06B6D4",
  teal: "#14B8A6",
  green: "#16A34A",
  amber: "#F59E0B",
  red: "#EF4444",
  violet: "#7C3AED",
  muted: "#64748B",
  line: "#D7E1EE",
  bg: "#F7FAFC",
  panel: "#FFFFFF",
  softBlue: "#EAF2FF",
  softCyan: "#E6FAFD",
  softGreen: "#EAFBF0",
  softAmber: "#FFF6E5",
  softRed: "#FEF2F2",
  softViolet: "#F3EDFF",
  softGray: "#EEF2F7",
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

function page(title, subtitle, children, opts = {}) {
  return panel(
    {
      name: `${opts.name || "slide"}-bg`,
      width: fill,
      height: fill,
      fill: opts.dark ? C.ink : C.bg,
      padding: { x: 82, y: 58 },
      materialize: true,
    },
    column({ width: fill, height: fill, gap: 24 }, [
      row({ width: fill, height: fixed(164), align: "center", gap: 24 }, [
        column({ width: fill, height: fill, gap: 10, justify: "center" }, [
          text(opts.eyebrow || "AMAN-AI | Partie binôme", {
            name: `${opts.name || "slide"}-eyebrow`,
            width: fill,
            height: hug,
            style: {
              typeface: "Aptos",
              fontSize: 16,
              bold: true,
              color: opts.dark ? "#8FD9FF" : C.blue,
            },
          }),
          text(title, {
            name: `${opts.name || "slide"}-title`,
            width: fill,
            height: hug,
            style: {
              typeface: "Aptos Display",
              fontSize: opts.titleSize || 54,
              bold: true,
              color: opts.dark ? "#FFFFFF" : C.ink,
            },
          }),
          text(subtitle, {
            name: `${opts.name || "slide"}-subtitle`,
            width: wrap(1280),
            height: hug,
            style: {
              typeface: "Aptos",
              fontSize: 22,
              color: opts.dark ? "#C7D2FE" : C.muted,
              lineSpacing: 1.05,
            },
          }),
        ]),
        panel(
          {
            width: fixed(150),
            height: fixed(46),
            fill: opts.dark ? "#FFFFFF" : C.ink,
            borderRadius: 23,
            align: "center",
            justify: "center",
            materialize: true,
          },
          text(opts.badge || "5 min", {
            width: fill,
            height: hug,
            style: {
              typeface: "Aptos",
              fontSize: 17,
              bold: true,
              color: opts.dark ? C.ink : "#FFFFFF",
              alignment: "center",
            },
          }),
        ),
      ]),
      rule({ width: fixed(190), stroke: opts.dark ? C.cyan : C.blue, weight: 5 }),
      ...children,
    ]),
  );
}

function node(label, detail, tone = "blue", width = 260, height = 118) {
  const compact = height <= 94;
  const palette = {
    blue: [C.softBlue, "#BBD2FF", C.blue],
    cyan: [C.softCyan, "#A7E9F3", C.cyan],
    teal: ["#E7FAF7", "#A8E6DC", C.teal],
    green: [C.softGreen, "#BBF7D0", C.green],
    amber: [C.softAmber, "#FBD38D", C.amber],
    red: [C.softRed, "#FECACA", C.red],
    violet: [C.softViolet, "#D8B4FE", C.violet],
    gray: [C.softGray, C.line, C.muted],
    white: [C.panel, C.line, C.ink],
  }[tone];
  return panel(
    {
      width: fixed(width),
      height: fixed(height),
      fill: palette[0],
      line: stroke(`1 ${palette[1]}`),
      borderRadius: 22,
      padding: { x: compact ? 14 : 18, y: compact ? 10 : 16 },
      materialize: true,
    },
    column({ width: fill, height: fill, gap: compact ? 4 : 7, justify: "center" }, [
      text(label, {
        width: fill,
        height: hug,
        style: {
          typeface: "Aptos Display",
          fontSize: compact ? 21 : 24,
          bold: true,
          color: C.ink,
          alignment: "center",
        },
      }),
      text(detail, {
        width: fill,
        height: hug,
        style: {
          typeface: "Aptos",
          fontSize: compact ? 14 : 16,
          color: C.muted,
          alignment: "center",
          lineSpacing: 1.05,
        },
      }),
      rule({ width: fixed(64), stroke: palette[2], weight: 4 }),
    ]),
  );
}

function arrow(label = "→", width = 44, color = C.blue) {
  return text(label, {
    width: fixed(width),
    height: fixed(42),
    style: {
      typeface: "Aptos Display",
      fontSize: 29,
      bold: true,
      color,
      alignment: "center",
    },
  });
}

function metric(value, label, tone = "blue") {
  const color = { blue: C.blue, green: C.green, amber: C.amber, red: C.red }[tone];
  return column({ width: fill, height: hug, gap: 3, align: "center" }, [
    text(value, {
      width: fill,
      height: hug,
      style: {
        typeface: "Aptos Display",
        fontSize: 44,
        bold: true,
        color,
        alignment: "center",
      },
    }),
    text(label, {
      width: fill,
      height: hug,
      style: {
        typeface: "Aptos",
        fontSize: 17,
        color: C.muted,
        alignment: "center",
      },
    }),
  ]);
}

function chip(label, color = C.blue) {
  return panel(
    {
      width: hug,
      height: fixed(38),
      fill: "#FFFFFF",
      line: stroke(`1 ${C.line}`),
      borderRadius: 19,
      padding: { x: 16, y: 8 },
      materialize: true,
    },
    text(label, {
      width: hug,
      height: hug,
      style: {
        typeface: "Aptos",
        fontSize: 15,
        bold: true,
        color,
        alignment: "center",
      },
    }),
  );
}

function openBullet(title, desc, color = C.blue) {
  return row({ width: fill, height: hug, gap: 14, align: "start" }, [
    shape({ geometry: "ellipse", width: fixed(12), height: fixed(12), fill: color }),
    column({ width: fill, height: hug, gap: 4 }, [
      text(title, {
        width: fill,
        height: hug,
        style: { typeface: "Aptos", fontSize: 23, bold: true, color: C.ink },
      }),
      text(desc, {
        width: fill,
        height: hug,
        style: { typeface: "Aptos", fontSize: 18, color: C.muted, lineSpacing: 1.05 },
      }),
    ]),
  ]);
}

// Slide 1: cover
{
  const slide = presentation.slides.add();
  compose(
    slide,
    panel(
      {
        name: "cover-bg",
        width: fill,
        height: fill,
        fill: C.ink,
        padding: { x: 96, y: 76 },
        materialize: true,
      },
      row({ width: fill, height: fill, align: "center", gap: 72 }, [
        column({ width: fixed(910), height: fill, justify: "center", gap: 22 }, [
          text("AMAN-AI", {
            width: fill,
            height: hug,
            style: {
              typeface: "Aptos",
              fontSize: 18,
              bold: true,
              color: "#8FD9FF",
            },
          }),
          text("Architecture technique & modèle de mouvement", {
            name: "cover-title",
            width: fill,
            height: hug,
            style: {
              typeface: "Aptos Display",
              fontSize: 74,
              bold: true,
              color: "#FFFFFF",
              lineSpacing: 0.98,
            },
          }),
          text("Résumé professionnel en 5 minutes : surveillance mobile, alerte automatique, Bi-LSTM SisFall et export TFLite.", {
            name: "cover-subtitle",
            width: wrap(900),
            height: hug,
            style: {
              typeface: "Aptos",
              fontSize: 25,
              color: "#CBD5E1",
              lineSpacing: 1.08,
            },
          }),
          row({ width: fill, height: hug, gap: 12 }, [
            chip("Architecture globale", C.cyan),
            chip("Détection mouvement", C.teal),
            chip("TFLite mobile", C.amber),
          ]),
        ]),
        column({ width: fill, height: fill, justify: "center", gap: 26 }, [
          row({ width: fill, height: hug, gap: 18, align: "center" }, [
            node("Capteurs", "micro + accéléromètre", "cyan", 250, 104),
            arrow("→", 48, "#8FD9FF"),
            node("IA locale", "audio + mouvement", "teal", 250, 104),
          ]),
          row({ width: fill, height: hug, gap: 18, align: "center", justify: "center" }, [
            arrow("↓", 48, "#8FD9FF"),
          ]),
          row({ width: fill, height: hug, gap: 18, align: "center" }, [
            node("Escalade", "seuil + compte à rebours", "amber", 250, 104),
            arrow("→", 48, "#8FD9FF"),
            node("Alerte", "REST / Firebase / SMS", "red", 250, 104),
          ]),
        ]),
      ]),
    ),
  );
  slide.speakerNotes.setText(
    "Dans cette partie, je présente le travail technique autour de la surveillance et du modèle de mouvement dans AMAN-AI. L'objectif est simple : transformer des signaux de capteurs en une décision fiable, puis déclencher une alerte si le risque est confirmé. La présentation est divisée en deux blocs. D'abord, l'architecture globale de l'application : capteurs, service en arrière-plan, inférence locale, stockage et transmission. Ensuite, la partie apprentissage automatique : dataset SisFall, préparation des données, choix du Bi-LSTM, résultats et export TFLite pour l'intégration mobile.",
  );
}

// Slide 2: global architecture
{
  const slide = presentation.slides.add();
  compose(
    slide,
    page(
      "Architecture technique globale",
      "Une chaîne complète : acquisition capteur, inférence locale, décision, puis transmission de l'alerte.",
      [
        column({ width: fill, height: fill, gap: 28, justify: "center" }, [
          row({ width: fill, height: hug, gap: 14, align: "center", justify: "center" }, [
            node("Capture", "micro + accéléromètre", "cyan", 238, 118),
            arrow(),
            node("Service background", "DetectionService / MonitoringService", "blue", 290, 118),
            arrow(),
            node("TFLite local", "AudioClassifier + MotionClassifier", "teal", 292, 118),
            arrow(),
            node("Décision", "fusion des scores + seuil 61 %", "amber", 270, 118),
            arrow(),
            node("Alerte", "REST, Firebase ou SMS fallback", "red", 292, 118),
          ]),
          panel(
            {
              width: fill,
              height: fixed(250),
              fill: "#FFFFFF",
              line: stroke(`1 ${C.line}`),
              borderRadius: 28,
              padding: { x: 34, y: 28 },
              materialize: true,
            },
            grid(
              {
                width: fill,
                height: fill,
                columns: [fr(1), fr(1), fr(1)],
                columnGap: 28,
                alignItems: "center",
              },
              [
                column({ width: fill, height: fill, gap: 12, justify: "center" }, [
                  text("Surveillance continue", {
                    width: fill,
                    height: hug,
                    style: { typeface: "Aptos Display", fontSize: 28, bold: true, color: C.ink },
                  }),
                  text("Le service tourne en foreground/background avec une notification de protection active. Il écoute les capteurs et prépare les données pour l'inférence.", {
                    width: fill,
                    height: hug,
                    style: { typeface: "Aptos", fontSize: 20, color: C.muted, lineSpacing: 1.07 },
                  }),
                ]),
                column({ width: fill, height: fill, gap: 12, justify: "center" }, [
                  text("Inférence embarquée", {
                    width: fill,
                    height: hug,
                    style: { typeface: "Aptos Display", fontSize: 28, bold: true, color: C.ink },
                  }),
                  text("Les modèles TFLite produisent des probabilités de menace. Le score final combine audio et mouvement pour limiter les fausses alertes.", {
                    width: fill,
                    height: hug,
                    style: { typeface: "Aptos", fontSize: 20, color: C.muted, lineSpacing: 1.07 },
                  }),
                ]),
                column({ width: fill, height: fill, gap: 12, justify: "center" }, [
                  text("Transmission robuste", {
                    width: fill,
                    height: hug,
                    style: { typeface: "Aptos Display", fontSize: 28, bold: true, color: C.ink },
                  }),
                  text("Si le réseau est disponible, l'alerte passe par le serveur/Firebase. Sinon, le canal SMS + GPS sert de fallback.", {
                    width: fill,
                    height: hug,
                    style: { typeface: "Aptos", fontSize: 20, color: C.muted, lineSpacing: 1.07 },
                  }),
                ]),
              ],
            ),
          ),
          row({ width: fill, height: hug, gap: 14, justify: "center" }, [
            chip("Code : DetectionService", C.blue),
            chip("ML : MotionClassifier / AudioClassifier", C.teal),
            chip("Alertes : AlertRepository / SmsSender", C.red),
            chip("Diagrammes : séquence + classes", C.violet),
          ]),
        ]),
      ],
      { name: "architecture", badge: "Architecture" },
    ),
  );
  slide.speakerNotes.setText(
    "L'architecture globale se lit comme une chaîne de décision. La première couche est la capture : microphone et accéléromètre. Ensuite, un service de surveillance en arrière-plan, représenté dans les diagrammes par MonitoringService et dans le code actuel par DetectionService, centralise l'écoute des capteurs. Les données sont ensuite envoyées vers les classifieurs locaux TFLite : un pour l'audio et un pour le mouvement. Les probabilités obtenues sont fusionnées pour calculer un score final. Si le score dépasse le seuil, l'application lance une escalade avec un compte à rebours. Enfin, l'alerte est transmise au contact via le canal disponible : serveur, Firebase, ou SMS avec coordonnées GPS en cas d'absence de réseau.",
  );
}

// Slide 3: alert flow
{
  const slide = presentation.slides.add();
  compose(
    slide,
    page(
      "Logique de monitoring et cycle d'alerte",
      "Le système évite l'alerte immédiate : il évalue, laisse une fenêtre d'annulation, puis choisit le canal d'envoi.",
      [
        row({ width: fill, height: fill, gap: 34, align: "center" }, [
          column({ width: fixed(620), height: fill, gap: 18, justify: "center" }, [
            text("États principaux", {
              width: fill,
              height: hug,
              style: { typeface: "Aptos Display", fontSize: 32, bold: true, color: C.ink },
            }),
            row({ width: fill, height: hug, gap: 14, align: "center" }, [
              node("IDLE", "service actif", "gray", 205, 88),
              arrow("→", 38),
              node("MONITORING", "écoute passive", "blue", 238, 88),
            ]),
            row({ width: fill, height: hug, gap: 14, align: "center" }, [
              node("EVALUATE", "score S", "teal", 205, 88),
              arrow("→", 38),
              node("COUNTDOWN", "30 s pour annuler", "amber", 238, 88),
            ]),
            row({ width: fill, height: hug, gap: 14, align: "center" }, [
              node("ROUTING", "choix canal", "violet", 205, 88),
              arrow("→", 38),
              node("SUCCESS", "alerte confirmée", "green", 238, 88),
            ]),
          ]),
          panel(
            {
              width: fill,
              height: fixed(590),
              fill: "#FFFFFF",
              line: stroke(`1 ${C.line}`),
              borderRadius: 30,
              padding: { x: 34, y: 30 },
              materialize: true,
            },
            column({ width: fill, height: fill, gap: 22, justify: "center" }, [
              row({ width: fill, height: hug, gap: 20, align: "center" }, [
                node("Score S ≤ 61 %", "retour au monitoring", "green", 260, 86),
                arrow("↺", 48, C.green),
                text("Pas d'escalade", {
                  width: fill,
                  height: hug,
                  style: { typeface: "Aptos", fontSize: 23, bold: true, color: C.green },
                }),
              ]),
              row({ width: fill, height: hug, gap: 20, align: "center" }, [
                node("Score S > 61 %", "risque détecté", "red", 260, 86),
                arrow("→", 48, C.red),
                text("Escalade active : vibration + minuteur 30 s", {
                  width: fill,
                  height: hug,
                  style: { typeface: "Aptos", fontSize: 23, bold: true, color: C.ink },
                }),
              ]),
              row({ width: fill, height: hug, gap: 20, align: "center" }, [
                node("PIN correct", "annulation utilisateur", "amber", 260, 86),
                arrow("→", 48, C.amber),
                text("Abort + retour IDLE", {
                  width: fill,
                  height: hug,
                  style: { typeface: "Aptos", fontSize: 23, bold: true, color: C.ink },
                }),
              ]),
              row({ width: fill, height: hug, gap: 20, align: "center" }, [
                node("Expiration 30 s", "détresse confirmée", "violet", 260, 86),
                arrow("→", 48, C.violet),
                text("REST/Firebase si réseau, SMS + GPS si hors-ligne", {
                  width: fill,
                  height: hug,
                  style: { typeface: "Aptos", fontSize: 23, bold: true, color: C.ink },
                }),
              ]),
              text("Les diagrammes associés couvrent : séquence complète, machine d'états et synchronisation des alertes offline.", {
                width: fill,
                height: hug,
                style: { typeface: "Aptos", fontSize: 19, color: C.muted, alignment: "center" },
              }),
            ]),
          ),
        ]),
      ],
      { name: "monitoring", badge: "Alert Flow" },
    ),
  );
  slide.speakerNotes.setText(
    "Le point important de cette logique est que l'application ne déclenche pas une alerte de manière brutale. Elle commence en état passif, elle surveille les signaux, puis elle évalue un score de menace. Si le score reste inférieur ou égal au seuil de 61 %, l'application retourne simplement au monitoring. Si le score dépasse ce seuil, une escalade commence : vibration, écran d'alerte et compte à rebours de 30 secondes. L'utilisateur peut annuler avec le PIN secret. Si le délai expire, la détresse est confirmée. À ce moment-là, le système choisit le meilleur canal : en ligne via REST et Firebase, ou hors-ligne via SMS avec localisation GPS. C'est ce qui rend le système plus robuste dans une situation réelle.",
  );
}

// Slide 4: SisFall and preprocessing
{
  const slide = presentation.slides.add();
  compose(
    slide,
    page(
      "Dataset SisFall et préparation",
      "Un benchmark public de fall detection, utilisé pour entraîner un modèle de mouvement fiable.",
      [
        column({ width: fill, height: fill, gap: 26, justify: "center" }, [
          row({ width: fill, height: hug, gap: 18, align: "center" }, [
            metric("19", "activités ADL", "blue"),
            metric("15", "types de chutes", "red"),
            metric("38", "participants", "green"),
            metric("IMU", "accéléro + gyro", "amber"),
          ]),
          panel(
            {
              width: fill,
              height: fixed(300),
              fill: "#FFFFFF",
              line: stroke(`1 ${C.line}`),
              borderRadius: 30,
              padding: { x: 34, y: 30 },
              materialize: true,
            },
            row({ width: fill, height: fill, gap: 14, align: "center", justify: "center" }, [
              node("Chargement", "fichiers capteurs", "gray", 214, 92),
              arrow(),
              node("Exploration", "shapes + classes", "blue", 214, 92),
              arrow(),
              node("Fenêtrage", "512 points", "cyan", 214, 92),
              arrow(),
              node("Normalisation", "Z-score train only", "teal", 236, 92),
              arrow(),
              node("Équilibrage", "class weights", "amber", 214, 92),
              arrow(),
              node("Train/Test", "évaluation finale", "green", 214, 92),
            ]),
          ),
          row({ width: fill, height: hug, gap: 18, align: "center", justify: "center" }, [
            chip("233k fenêtres train", C.blue),
            chip("entrée modèle : 512 × 1", C.teal),
            chip("class weights : 0.75 / 1.5", C.amber),
            chip("mean=-0.03061 | std=0.10518", C.violet),
          ]),
          text("Source dataset : Sucerquia et al., SisFall: A Fall and Movement Dataset, 2017", {
            width: fill,
            height: hug,
            style: { typeface: "Aptos", fontSize: 16, color: C.muted, alignment: "center" },
          }),
        ]),
      ],
      { name: "sisfall", badge: "Dataset" },
    ),
  );
  slide.speakerNotes.setText(
    "Pour entraîner le modèle de mouvement, le dataset utilisé est SisFall. C'est une base connue dans les travaux de détection de chute, car elle contient à la fois des activités normales de la vie quotidienne et plusieurs types de chutes. Elle est donc intéressante pour comparer un mouvement normal avec un événement dangereux. Dans le notebook, la première étape consiste à charger les données capteurs, vérifier les formes et les classes, puis découper les signaux en fenêtres de 512 points. La normalisation est faite avec un Z-score calculé uniquement sur le train, pour éviter toute fuite d'information. Enfin, l'équilibrage est géré avec des poids de classes, parce que les chutes sont moins fréquentes que les mouvements normaux.",
  );
}

// Slide 5: Bi-LSTM logic
{
  const slide = presentation.slides.add();
  compose(
    slide,
    page(
      "Logique du modèle Bi-LSTM",
      "Un mouvement dangereux est une séquence temporelle, pas une valeur isolée.",
      [
        row({ width: fill, height: fill, gap: 36, align: "center" }, [
          column({ width: fixed(570), height: fill, gap: 28, justify: "center" }, [
            openBullet("Pourquoi LSTM ?", "Il mémorise les dépendances dans le temps : accélération, rupture, impact, stabilisation.", C.blue),
            openBullet("Pourquoi bidirectionnel ?", "Il lit le contexte avant et après dans la fenêtre pour mieux comprendre la dynamique.", C.teal),
            openBullet("Pourquoi léger ?", "Deux couches de 32 unités gardent un bon compromis entre précision et exécution mobile.", C.amber),
          ]),
          panel(
            {
              width: fill,
              height: fixed(545),
              fill: "#FFFFFF",
              line: stroke(`1 ${C.line}`),
              borderRadius: 30,
              padding: { x: 34, y: 34 },
              materialize: true,
            },
            column({ width: fill, height: fill, gap: 32, justify: "center" }, [
              row({ width: fill, height: hug, gap: 9, align: "center", justify: "center" }, [
                node("Input", "512 × 1", "gray", 136, 92),
                arrow("→", 32),
                node("Bi-LSTM 1", "32 unités", "blue", 166, 92),
                arrow("→", 32),
                node("Bi-LSTM 2", "32 unités", "cyan", 166, 92),
                arrow("→", 32),
                node("Dense", "Dropout + sigmoid", "violet", 186, 92),
                arrow("→", 32),
                node("p(chute)", "score final", "red", 156, 92),
              ]),
              row({ width: fill, height: hug, gap: 18, justify: "center" }, [
                chip("fenêtrage 512 points", C.blue),
                chip("normalisation sauvegardée", C.teal),
                chip("sortie binaire", C.red),
              ]),
              text("La fenêtre donne au modèle assez de contexte pour détecter un changement brutal du mouvement.", {
                width: fill,
                height: hug,
                style: { typeface: "Aptos Display", fontSize: 30, bold: true, color: C.ink, alignment: "center" },
              }),
            ]),
          ),
        ]),
      ],
      { name: "bilstm", badge: "Bi-LSTM" },
    ),
  );
  slide.speakerNotes.setText(
    "Le choix du Bi-LSTM vient de la nature du problème. Une chute ou un mouvement violent n'est pas seulement une valeur élevée sur l'accéléromètre. C'est une évolution temporelle : un mouvement rapide, une rupture, parfois un impact, puis une stabilisation. Le LSTM est adapté parce qu'il mémorise ces dépendances temporelles. La version bidirectionnelle permet de lire la fenêtre dans les deux sens, ce qui donne plus de contexte pendant l'entraînement. Le pipeline est simple : une fenêtre normalisée de 512 points entre dans deux couches Bi-LSTM, puis une partie dense avec sigmoid donne la probabilité de chute. L'objectif était d'avoir un modèle performant, mais encore assez léger pour être converti et utilisé sur mobile.",
  );
}

// Slide 6: results and mobile integration
{
  const slide = presentation.slides.add();
  compose(
    slide,
    page(
      "Résultats et intégration mobile",
      "Le modèle est performant, léger, et prêt à être appelé côté Android pour déclencher l'alerte.",
      [
        column({ width: fill, height: fill, gap: 28, justify: "center" }, [
          row({ width: fill, height: hug, gap: 24, align: "center" }, [
            metric("95.68%", "test accuracy", "blue"),
            metric("0.9902", "AUC-ROC", "green"),
            metric("95.61%", "recall chute", "amber"),
            metric("4.29%", "fausses alertes", "red"),
          ]),
          panel(
            {
              width: fill,
              height: fixed(300),
              fill: "#FFFFFF",
              line: stroke(`1 ${C.line}`),
              borderRadius: 30,
              padding: { x: 34, y: 30 },
              materialize: true,
            },
            row({ width: fill, height: fill, gap: 18, align: "center", justify: "center" }, [
              node("Keras .h5", "backup modèle", "gray", 215, 92),
              arrow(),
              node("TFLite INT8", "73 KB", "blue", 215, 92),
              arrow(),
              node("mean / std", "normalisation", "teal", 215, 92),
              arrow(),
              node("App Android", "inférence locale", "violet", 225, 92),
              arrow(),
              node("Alerte", "score > seuil", "red", 205, 92),
            ]),
          ),
          row({ width: fill, height: hug, gap: 18, justify: "center" }, [
            chip("models/bilstm_int8.tflite", C.blue),
            chip("norm_mean.npy", C.teal),
            chip("norm_std.npy", C.teal),
            chip("priorité : ne pas rater une chute", C.red),
          ]),
          text("Conclusion : la partie IA transforme le signal mouvement en score exploitable par AMAN-AI pour une alerte locale et rapide.", {
            width: fill,
            height: hug,
            style: { typeface: "Aptos Display", fontSize: 29, bold: true, color: C.ink, alignment: "center" },
          }),
        ]),
      ],
      { name: "results", badge: "TFLite" },
    ),
  );
  slide.speakerNotes.setText(
    "Les résultats montrent que le modèle atteint une précision de 95,68 % sur le test, avec une AUC de 0,9902. Le recall chute est de 95,61 %, ce qui est très important dans notre contexte, parce que rater une chute est plus critique qu'une fausse alerte occasionnelle. Après l'entraînement, le modèle est sauvegardé en Keras, puis converti en TensorFlow Lite INT8. Le fichier final est léger, environ 73 KB, et les paramètres de normalisation sont sauvegardés séparément avec la moyenne et l'écart-type. Côté application mobile, l'idée est d'utiliser ce modèle localement pour produire un score de risque. Si ce score dépasse le seuil défini, il alimente la logique d'alerte AMAN-AI. En résumé, cette partie relie l'apprentissage automatique au besoin concret de l'application : détecter plus vite et déclencher une alerte utile.",
  );
}

const pptxBlob = await PresentationFile.exportPptx(presentation);
await pptxBlob.save(OUT);

const previewPaths = [];
for (let i = 0; i < presentation.slides.count; i += 1) {
  const slide = presentation.slides.getItem(i);
  const png = await slide.export({ format: "png" });
  const pngPath = path.join(SCRATCH, `slide-${i + 1}.png`);
  fs.writeFileSync(pngPath, Buffer.from(await png.arrayBuffer()));
  previewPaths.push(pngPath);
  const layout = await slide.export({ format: "layout" });
  fs.writeFileSync(path.join(SCRATCH, `slide-${i + 1}.layout.json`), JSON.stringify(layout, null, 2), "utf8");
}

console.log(JSON.stringify({ pptx: OUT, previews: previewPaths }, null, 2));

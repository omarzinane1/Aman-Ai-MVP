import fs from "node:fs";
import path from "node:path";
import { PresentationFile } from "@oai/artifact-tool";

const ROOT = path.resolve(".");
const OUT = path.join(ROOT, "output", "aman_ai_architecture_motion_5min.pptx");
const SCRATCH = path.join(ROOT, "scratch");
fs.mkdirSync(SCRATCH, { recursive: true });

const bytes = fs.readFileSync(OUT);
const imported = await PresentationFile.importPptx(new Blob([bytes], {
  type: "application/vnd.openxmlformats-officedocument.presentationml.presentation",
}));
const previews = [];
for (let i = 0; i < imported.slides.count; i += 1) {
  const slide = imported.slides.getItem(i);
  const png = await slide.export({ format: "png" });
  const pngPath = path.join(SCRATCH, `saved-slide-${i + 1}.png`);
  fs.writeFileSync(pngPath, Buffer.from(await png.arrayBuffer()));
  previews.push(pngPath);
}

console.log(JSON.stringify({
  slides: imported.slides.count,
  savedPreviews: previews,
}, null, 2));

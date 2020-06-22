import express from "express";
import data from "./data";

const app = express();

app.get("/api/currencies", (req, res) => {
  res.send(data.currencies);
});

app.get("/api/gst", (req, res) => {
  res.send(data.gst);
});

app.get("/api/owner", (req, res) => {
  res.send(data.owner);
});

app.listen(5000, () => {
  console.log("Listening to 5000");
});

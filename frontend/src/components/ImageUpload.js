import Button from "./Login/Button";
import { React } from "react";
import { Card, Container } from "react-bootstrap";
import { BiImages as AiFillPicture } from "react-icons/bi";
import { IoMdCamera as FaCamera } from "react-icons/io";
import DragandDrop from "./Login/DragandDrop";

const ImageUpload = (props) => {
  let onChange = props.onChange;
  let id = props.imgID
  return (
    <div>
      <Card.Body className="p-1 pt-3 pb-3 bg-white">
        <Container>
          <Container className="border nichtRund">
            <div id={"imgupload"+ id}>
              <Card.Body className="p-0 m-3">
                <DragandDrop
                  imgID={id}
                  onDrop={function () {
                    imgToCanvas(id, function (can) {
                      onChange(can);
                    });
                  }}
                  className="m-0 w-100"
                ></DragandDrop>
              </Card.Body>
            </div>
            <div className="hide" id={"cams"+ id}>
              <div id={"camVideo"+ id}>
                <video
                  className="center border"
                  id = {"video"+ id}
                  width="640"
                  height="480"
                  autoPlay
                ></video>
                <Button
                  text="Foto schießen"
                  className2="w-100 mt-4"
                  variant="secondary"
                  onClick={function () {
                    var canvas = document.getElementById("canvas" + id);
                    var context = canvas.getContext("2d");
                    var video = document.getElementById("video" + id);

                    context.drawImage(video, 0, 0, 640, 480);
                    document.getElementById("camVideo"+ id).classList.add("hide");
                    document.getElementById("canvas"+ id).classList.remove("hide");

                    onChange(document.getElementById("canvas"+ id));
                  }}
                ></Button>
              </div>
              <canvas
                id={"canvas"+ id}
                className="hide center border"
                width="640"
                height="480"
              ></canvas>
            </div>

            <Card.Footer className="mt-4 p-0 bg-white">
              <Button
                text={<AiFillPicture className="Icon" />}
                className2=""
                variant="secondary"
                onClick={function () {
                  document
                    .getElementById("cams" + id)
                    .classList.add("hide");
                  document
                    .getElementById("imgupload" + id)
                    .classList.remove("hide");
                }}
              ></Button>
              <Button
                text={<FaCamera className="Icon" />}
                className="m-4"
                className2="m-2"
                variant="secondary"
                onClick={function () {
                  document
                    .getElementById("imgupload" + id)
                    .classList.add("hide");
                  document
                    .getElementById("cams" + id)
                    .classList.remove("hide");

                  var video = document.getElementById("video"+ id);
                  console.log(video)
                  // Get access to the camera!
                  if (
                    navigator.mediaDevices &&
                    navigator.mediaDevices.getUserMedia
                  ) {
                    // Not adding `{ audio: true }` since we only want video now
                    navigator.mediaDevices
                      .getUserMedia({ video: true })
                      .then(function (stream) {
                        //video.src = window.URL.createObjectURL(stream);
                        video.srcObject = stream;
                        video.play();
                      });
                  }
                }}
              ></Button>
            </Card.Footer>
          </Container>
        </Container>
      </Card.Body>
    </div>
  );
};

export default ImageUpload;

async function imgToCanvas(id, callback) {
  var img2 = document.getElementById(id);
  var img = new Image();
  img.src = img2.src;
  img.crossOrigin = "Anonymous";
  img.onload = function () {
    var canvas = document.createElement("CANVAS");
    var ctx = canvas.getContext("2d");

    canvas.height = img.height;
    canvas.width = img.width;
    ctx.drawImage(this, 0, 0);
    callback(canvas);
  };
}

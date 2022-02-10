import React, { useState } from "react";
import { useDropzone } from "react-dropzone";

function DragandDrop(props) {
  let id = props.imgID;

  const [files, setFiles] = useState([]);
  const { getRootProps, getInputProps } = useDropzone({
    accept: "image/*",
    multiple:false,
    onDrop: (acceptedFiles) => {
      setFiles(
        acceptedFiles.map((file) =>
          Object.assign(file, {
            preview: URL.createObjectURL(file),
          })
        )
      );
       document.getElementById("dadPlatzhalter"+id).classList.add("hide");
       props.onDrop();
    },
  });

  const images = files.map((file) => (
    <div key={file.name} className=" w-100 ">
      <div className="w-100">
        <img
          className="w-100 rounded-lg center autosize"
          src={file.preview}
          style={{ width: "200px" }}
          alt="preview"
          id = {id}
        />
      </div>
    </div>
  ));

  return (
    <div>
      <div className="dad" {...getRootProps()}>
        <input {...getInputProps()} />
        <div className = "w-100 " id={"dadPlatzhalter"+id}>Bild hier Hochladen</div>
        <div className="draganddopBild w-100">{images}</div>
      </div>
    </div>
  );
}

export default DragandDrop;

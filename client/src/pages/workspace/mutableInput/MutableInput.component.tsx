import React, { useState, useRef, useEffect, ChangeEvent, KeyboardEvent } from "react";
import OutsideClickHandler from 'react-outside-click-handler';

import "./MutableInput.style.css";
import {MutableInputProps} from "./props";

const MutableInput: React.FC<MutableInputProps> = ({ text, textSet, placeHolder }) => {
    const currentField = useRef<HTMLTextAreaElement>(null);
    const [edit, editSet] = useState<boolean>(false);

    useEffect(() => {
        if (edit) {
            currentField.current?.select();
            currentField.current!.style.height = currentField.current?.scrollHeight + "px";
        }
    }, [edit]);

    const autoSizeGrow = (e: ChangeEvent<HTMLTextAreaElement>) => {
        e.target.style.height = 'auto';
        e.target.style.height = e.target.scrollHeight + "px";
    };

    const handleKeyPress = (e: KeyboardEvent<HTMLTextAreaElement> | "outside") => {
        if (e === "outside" || e.key === "Enter") {
            if (text === "") {
                textSet(placeHolder);
            }
            editSet(false);
        }
    };

    const handleInputChange = (e: ChangeEvent<HTMLTextAreaElement>) => {
        textSet(e.target.value.trimStart());
    };

    return (
        <OutsideClickHandler onOutsideClick={() => handleKeyPress("outside")}>
            <div id="mi" className={"mi-div"}>
                {edit ? (
                    <textarea
                        id="mi-textarea"
                        className={"mi-textarea"}
                        onInput={autoSizeGrow}
                        value={text}
                        onChange={handleInputChange}
                        onKeyPress={handleKeyPress}
                        ref={currentField}
                        rows={1}
                    />
                ) : (
                    <div onClick={() => editSet(true)} id="mi-div">
                        {text}
                    </div>
                )}
            </div>
        </OutsideClickHandler>
    );
}

export default MutableInput;
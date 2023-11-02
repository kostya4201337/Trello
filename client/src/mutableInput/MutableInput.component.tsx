import React, { useState, useRef, useEffect, ChangeEvent, KeyboardEvent } from "react";
import OutsideClickHandler from 'react-outside-click-handler';

import "./styles.css";
import {MutableInputProps} from "./props";

const MutableInput: React.FC<MutableInputProps> = ({ text, textSet, placeHolder, className }) => {
    const currentField = useRef<HTMLTextAreaElement>(null);
    const [edit, setEdit] = useState<boolean>(false);

    useEffect(() => {
        if (edit) {
            selectField();
        }
    }, [edit]);

    const selectField = () => {
        currentField.current?.select();
        currentField.current!.style.height = currentField.current?.scrollHeight + "px";
    }

    const autoSizeGrow = (e: ChangeEvent<HTMLTextAreaElement>) => {
        e.target.style.height = 'auto';
        e.target.style.height = e.target.scrollHeight + "px";
    };

    const handleKeyPress = (e: KeyboardEvent<HTMLTextAreaElement> | "outside") => {
        if (e === "outside" || e.key === "Enter") {
            if (text === "") {
                textSet(placeHolder);
            }
            setEdit(false);
        }
    };

    const handleInputChange = (e: ChangeEvent<HTMLTextAreaElement>) => {
        textSet(e.target.value.trimStart());
    };

    return (
        <OutsideClickHandler onOutsideClick={() => handleKeyPress("outside")}>
            <div id="mi" className={"mi-div " + className}>
                {edit ? (
                    <textarea
                        id="mi-textarea"
                        className={"mi-textarea " + className}
                        onInput={autoSizeGrow}
                        value={text}
                        onChange={handleInputChange}
                        onKeyDown={handleKeyPress}
                        ref={currentField}
                        rows={1}
                    />
                ) : (
                    <div onClick={() => setEdit(true)} id="mi-div">
                        {text}
                    </div>
                )}
            </div>
        </OutsideClickHandler>
    );
}

export default MutableInput;
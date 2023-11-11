import React, { useState, useRef, useEffect, ChangeEvent, KeyboardEvent } from "react";
import OutsideClickHandler from 'react-outside-click-handler';

import "./styles.css";
import {MutableInputProps} from "./props";

const MutableInput: React.FC<MutableInputProps> = ({ text, setText, placeHolder, className, onChange }) => {
    const currentField = useRef<HTMLTextAreaElement>(null);
    const [edit, setEdit] = useState<boolean>(false);

    useEffect(() => {
        selectField();
    }, [edit]);

    const selectField = () => {
        if (edit) {
            currentField.current?.select();
            currentField.current!.style.height = currentField.current?.scrollHeight + "px";
        }
    }

    const autoSizeGrow = (e: ChangeEvent<HTMLTextAreaElement>) => {
        e.target.style.height = 'auto';
        e.target.style.height = e.target.scrollHeight + "px";
    };

    const handleKeyPress = async (e: KeyboardEvent<HTMLTextAreaElement> | "outside") => {
        if (e === "outside" || e.key === "Enter") {
            if (text === "") {
                setText(placeHolder);
            }
            setEdit(false);
            await onChange(text);
        }
    };

    const handleInputChange = (e: ChangeEvent<HTMLTextAreaElement>) => {
        setText(e.target.value.trimStart());
    };

    return (
        <OutsideClickHandler onOutsideClick={() => handleKeyPress("outside")}>
            <div className={"miDiv " + className}>
                {edit ? (
                    <textarea
                        className={"miTextarea " + className}
                        onInput={autoSizeGrow}
                        value={text}
                        onChange={handleInputChange}
                        onKeyDown={handleKeyPress}
                        ref={currentField}
                        rows={1}
                    />
                ) : (
                    <div onClick={() => setEdit(true)}>
                        {text}
                    </div>
                )}
            </div>
        </OutsideClickHandler>
    );
}

export default MutableInput;
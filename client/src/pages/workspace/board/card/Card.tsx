import "./styles.css";
import React, {useState} from "react";
import {CardProps} from "./props";
import MutableInput from "../../../../component/mutableInput/MutableInput";
import {IconButton} from "@mui/material";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";

const Card: React.FC<CardProps> = ({card, deleteCard}: CardProps) => {
    const [description, setDescription] = useState(card.description);
    const [name, setName] = useState(card.name);

    return (
        <div className={"card"}>
            <div className={"inline"}>
                <MutableInput className={"cardTitle"} text={name} placeHolder="" setText={setName}/>
                <IconButton className={"cardDeleteButton"} aria-label="delete" onClick={() => deleteCard(card.id)}>
                    <DeleteForeverIcon className={"cardInvert"}/>
                </IconButton>
            </div>
            <MutableInput className={"description"} text={description} placeHolder="" setText={setDescription}/>
            <div className={"inline cardBottomInfo"}>
                <div className={"taskId"}>TSK-{card.id}</div>
                <div className={"date"}>
                    {('0' + card.createdAt.getDate()).slice(-2)}.
                    {('0' + (card.createdAt.getMonth() + 1)).slice(-2)}.
                    {card.createdAt.getFullYear()}
                </div>
            </div>
        </div>
    );
};

export default Card;
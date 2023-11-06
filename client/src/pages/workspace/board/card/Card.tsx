import "./styles.css";
import React, {useEffect, useState} from "react";
import {CardProps} from "./props";
import MutableInput from "../../../../component/mutableInput/MutableInput";
import {IconButton} from "@mui/material";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import {ICard} from "../../../../model/Card";

const Card: React.FC<CardProps> = ({card, deleteCard}: CardProps) => {
    const [description, setDescription] = useState(card.description);
    const [name, setName] = useState(card.name);

    const [date, setDate] = useState<string>("");

    useEffect(() => {
        formatDate(card)
    }, [card]);

    const formatDate = (card: ICard) => {
        const createdDate = new Date(card.createdAt);
        const year = createdDate.getFullYear().toString();
        const month = (createdDate.getMonth() + 1).toString().padStart(2, '0');
        const day = createdDate.getDate().toString().padStart(2, '0');
        const formattedDate = `${day}.${month}.${year}`;
        setDate(formattedDate);
    }

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
                <div className={"date"}>{date}</div>
            </div>
        </div>
    );
};

export default Card;
import {ICard} from "../../../../model/Card";
export type CardProps = {
    card: ICard,
    deleteCard: (id: number) => void
}